package com.crawler.lastfm;

import com.google.gson.annotations.SerializedName;
// Object From Json recentTracks
public class RecentTracks {
	@SerializedName("@attr")
	private Attributes attr;
	
	public Attributes getAttr() {
		return attr;
	}

	public boolean isValidUser(){
		return attr!=null && attr.hasMinCountsToConsider();
		
	}



}
