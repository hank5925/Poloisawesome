package com.crawler.lastfm;
// Parent Object for User Details
public class UserDetails {

	private User user;
	private int error;
	private String message;
	
	public int getError() {
		return error;
	}
	public String getMessage() {
		return message;
	}


	public User getUser() {
		return user;
	}

	public boolean isUsrDetAvl(){
		return user!=null && user.isUserDetailsAvl();
	}

	public String toString() {
	    //return user.getName() +" "+user.getRealname();
		user.toString();
	    return error+","+message;
	  }
}
