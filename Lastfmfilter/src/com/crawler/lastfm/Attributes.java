package com.crawler.lastfm;


//This class gets The attributes in recentTracks method
// looking on the attribute of total we can include/exclude the user
public class Attributes {
	private String user;
	private int page;
	private int totalPages;

	private long total;
	private int perPage;

	public int getTotalPages() {
		return totalPages;
	}

	public boolean hasMinCountsToConsider() {
		if (total >= Constants.MINIMUM_PLAY_COUNT) {
			return true;
		}
		return false;

	}

	public String toString() {
		// return user.getName() +" "+user.getRealname();
		return user + "," + page + "," + perPage + "," + totalPages + ","
				+ total;
	}
}
