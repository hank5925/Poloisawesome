package com.crawler.lastfm;

import java.io.File;

public class FilterUsers {

	
	
	
	public static void main(String[] args) throws Exception {
		
		for(String userId : FileOperations.readFile(Constants.VALID_USERS_ID_FILE)){
			
			String paddedUsrId = Padder.formatStr(userId, Integer.valueOf(Constants.PADDING_LENGTH), Constants.PADDING_CHARACTER);
			String url = Constants.USERS_JSON_DIRECTORY+paddedUsrId+"/user_info.json";
			UserDetails usrDet = (UserDetails)IpStreamToFile.getJavaObjFromStream(ConnectionHandler.getStream(url,true),UserDetails.class);
			
			if(usrDet!=null && usrDet.getError()==0&&usrDet.isUsrDetAvl()){
				usrDet.toString();
				FileOperations.addValidUsersinList(usrDet.getUser().getName(), Constants.FILE_VALID_USERS_ID_FILTERED);
				FileOperations.copyFolder(new File(Constants.USERS_JSON_DIRECTORY+paddedUsrId), new File(Constants.DIRECTORY_USERS_JSON_VALID+paddedUsrId));
			}
		}
		
	}
}
