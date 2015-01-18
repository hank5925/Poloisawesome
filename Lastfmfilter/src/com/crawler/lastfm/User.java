package com.crawler.lastfm;
//Object For UserInfo from LastFm Json
public class User {
	private String name;
	public String getName() {
		return name;
	}
	public String getRealname() {
		return realname;
	}
	public String getId() {
		return id;
	}
	public int getPlaycount() {
		return playcount;
	}
	public String getUrl() {
		return url;
	}
	public String getCountry() {
		return country;
	}
	public String getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}
	public String getSubscriber() {
		return subscriber;
	}
	public String getPlaylists() {
		return playlists;
	}
	private String realname;
	private String id;
	private int playcount;
	private String url;
	private String country;
	private String age;
	private String gender;
	private String subscriber;
	private String playlists;
	
	public boolean isValidUser(){
		if(playcount>Constants.MINIMUM_PLAY_COUNT){
			return true;
		}
		return false;
	}
	
	public boolean isUserDetailsAvl(){
		if(id!=null && country!=null && age!=null && gender!=null && playcount!=0){
			if(age.length()>0&&gender.length()==1 && country.length()>0){
				return true;
			}
			
		}
		return false;
	}
	@Override
	public String toString() {
		System.out.println("Name:"+name);
		System.out.println("RealName:"+realname);
		System.out.println("Url:"+url);
		System.out.println("Id:"+id);
		System.out.println("Country:"+country);
		System.out.println("Age:"+age);
		System.out.println("Gender:"+gender);
		System.out.println("PlayCount:"+playcount);
		return super.toString();
	}
	

	
}
