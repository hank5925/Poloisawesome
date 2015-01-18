package com.crawler.lastfm;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.io.output.ByteArrayOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class UserInfoFetchImpl {
	
	
	//This method call the UserInfo method and get the Connection Open and Returns Input Stream
	public static InputStream fetchUserInfo(String userId) throws Exception {
		String urlToHit = Constants.USER_INFO_URL + URLEncoder.encode(userId)+ "&api_key=" + Constants.API_KEY+"&format=json";
		//System.out.println("UserInfo Url: "+urlToHit);
		URL url = new URL(urlToHit);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent",Constants.PROJECT_NAME_FOR_LAST_FM);
		Thread.sleep(25);
		return (conn.getInputStream());

	}
	//This method call the RecentTracks method with pageNumber and get the Connection Open and Returns Input Stream
	public static InputStream fetchTracksHistoryOfUser(String userId,int page) throws Exception {
		String urlToHit = Constants.USER_RECENT_TRACKS_URL + URLEncoder.encode(userId)+ "&api_key=" + Constants.API_KEY+Constants.LIMIT+Constants.FROM_DATE+"&page="+page+"&format=json";
		URL url = new URL(urlToHit);
		//System.out.println("Recent Tracks Url: "+url);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent",Constants.PROJECT_NAME_FOR_LAST_FM);
		Thread.sleep(25);
		return (conn.getInputStream());

	}
	
	//Delay The process to avoid id getting blocked
	public static void waitForNextCall(long difference) throws Exception{
		int timeTowaitForHalfSecondToComplete = (int) (Constants.NUM_OF_MILLI_SECS_FOR_ONE_API_CALL - difference);
		if (timeTowaitForHalfSecondToComplete <= 0) {
			timeTowaitForHalfSecondToComplete = 1;
		}
		else{
			//System.out.println("Waiting for "+timeTowaitForHalfSecondToComplete+" ms...");
		}
		Thread.sleep(timeTowaitForHalfSecondToComplete);
	}
	
	
	//This Method Saves the Track History of User & its UserInfo if User passes the Criteria to be included
	public static void saveUsersTrackRecord(String userId) throws Exception{
		InputStream is = fetchTracksHistoryOfUser(userId,1);
		Reader reader = new InputStreamReader(is);
	    JsonElement elem = new JsonParser().parse(reader);
	    Gson gson  = new GsonBuilder().create();
	    UserRecentTracks usrRecTracks = gson.fromJson(elem, UserRecentTracks.class);
	    if(usrRecTracks.getError()==0){
	    	//User Exists
	    	// wait for 500 ms
	    	waitForNextCall(500);
	    	
	    	//Check if User can be counted
	    	if(usrRecTracks.countThisUser()){
	    		//System.out.println("Going to save user Tracks for id "+userId);
	    		String formattedPage="";
    			String formattedId="";
    			
    			//Pad the userId
		    	if(userId.length()<Integer.parseInt(Constants.PADDING_LENGTH)){
		    		formattedId = String.format("%"+Constants.PADDING_LENGTH+"s", userId).replace(' ', Constants.PADDING_CHARACTER);
		    	}
		    	
		    	
		    	long end_time = 0;
		    	long start_time = 0;
	    		
		    	//Process the different pages for Recent Tracks
		    	for(int i=1;i<=usrRecTracks.getRecenttracks().getAttr().getTotalPages();i++){ // Excluded Condition &&i<Constants.MAX_PAGES;
	    			System.out.println("Going to save user Tracks for id "+userId+", Page "+i);
	    			if(String.valueOf(i).length()<Constants.PADDING_LENGTH_PAGE){
			    		formattedPage = String.format("%"+Constants.PADDING_LENGTH_PAGE+"s", i).replace(' ', Constants.PADDING_CHARACTER);
			    	}
	    			
	    			end_time = System.currentTimeMillis();
					long difference=end_time - start_time;
					waitForNextCall(difference);
			    	//Save the RecentTracks Page Json
					IpStreamToFile.saveRecords(Constants.USERS_JSON_DIRECTORY+formattedId+"/"+formattedPage+Constants.PAGE_EXTENSION,fetchTracksHistoryOfUser(userId,i));
			    	start_time = System.currentTimeMillis();
	    		}
	    		waitForNextCall(500);
	    		
	    		// After all the pages are Saved , save User Info
	    		saveUserInfo(userId);
	    	}
	    	
	    }
	}
	
	
	//This Method saves The UserInfo
	public static void saveUserInfo(String userId) throws Exception{
		System.out.println("Going to save user Info for id "+userId);
		InputStream is = fetchUserInfo(userId);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    org.apache.commons.io.IOUtils.copy(is, baos);
	    byte[] bytes = baos.toByteArray();
	    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	  
		Reader reader = new InputStreamReader(bais);
	    JsonElement elem = new JsonParser().parse(reader);
	    Gson gson  = new GsonBuilder().create();
	    UserDetails userInfo = gson.fromJson(elem, UserDetails.class);
	    
	    //Check if user is valid and no errors
	    if(userInfo!=null && userInfo.getError()==0){
	    	User user = userInfo.getUser();
		    if(user!=null && user.isValidUser()){
		    	//Add the userId in user file
		    	FileOperations.addValidUsersinList(userId,Constants.VALID_USERS_ID_FILE);
		    	System.out.println("Valid User Found"+ userId);
		    	//Padding for UserId
		    	String formattedId="";
		    	if(userId.length()<Integer.parseInt(Constants.PADDING_LENGTH)){
		    		formattedId = String.format("%"+Constants.PADDING_LENGTH+"s", userId).replace(' ', Constants.PADDING_CHARACTER);
		    	}
		    	ByteArrayInputStream baisSave = new ByteArrayInputStream(bytes);
		    	//Save the UserInfo File
		    	IpStreamToFile.saveRecords(Constants.USERS_JSON_DIRECTORY+formattedId+"/user_info"+Constants.USER_INFO_FILE_EXTENSION,baisSave);
		    
		    }else{
		    	//System.out.println("Invalid User,"+user.getId()+" PlayCount "+user.getPlaycount());
		    	
		    }
	    }else{
	    	//System.out.println(obj.getError()+" , "+obj.getMessage());
	    }
	}
	public static void persistData(){
		
			long end_time = System.currentTimeMillis();
			long startId = FileOperations.getlastvalidUserFromFile()+1;
			for(; startId<Constants.USER_ID_END_POSITION; startId++){
				try{
				String userId = String.valueOf(startId);
				end_time = System.currentTimeMillis();
				long start_time = System.currentTimeMillis();
				long difference=end_time - start_time;
				waitForNextCall(difference);	
				saveUsersTrackRecord(userId);
				}catch(Exception e) {
					try {
						//e.printStackTrace();
						System.err.println("Got Exception... Will Continue the next iteration afer a minute");
						System.err.println(e.getMessage());
						waitForNextCall(60000);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
		}
		
	

	public static void main(String[] args) {
		
			persistData();
		
	}
}
