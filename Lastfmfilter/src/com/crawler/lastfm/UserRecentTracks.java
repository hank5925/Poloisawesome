package com.crawler.lastfm;

public class UserRecentTracks {
	private RecentTracks recenttracks;
	public RecentTracks getRecenttracks() {
		return recenttracks;
	}


	public int getError() {
		return error;
	}


	public String getMessage() {
		return message;
	}


	private int error;
	private String message;
	
	
	public boolean countThisUser(){
		return recenttracks!=null && recenttracks.isValidUser();
	}
	
}
