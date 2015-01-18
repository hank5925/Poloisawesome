package com.crawler.lastfm;

public class Padder {
	
	public static String formatStr(String userId,int length,char chr){
		if(userId.length()<length){
    		return String.format("%"+length+"s", userId).replace(' ', chr);
    	}
		return userId;
	}

}
