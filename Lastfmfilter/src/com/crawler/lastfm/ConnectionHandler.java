package com.crawler.lastfm;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class ConnectionHandler {
	
	
	//This method call the UserInfo method and get the Connection Open and Returns Input Stream
	public static InputStream getStream(String url, boolean isLocalFile) throws Exception {
		URL connectionUrl=null;
		if(isLocalFile){
			connectionUrl =new File(url).toURI().toURL();
		}else{
			connectionUrl= new URL(url);
		}
		 
		URLConnection conn = connectionUrl.openConnection();
		conn.setRequestProperty("User-Agent",Constants.PROJECT_NAME_FOR_LAST_FM);
		Thread.sleep(25);
		return (conn.getInputStream());

	}
	


}
