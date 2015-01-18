package com.crawler.lastfm;

public class Constants {
	
	public static final String API_KEY="48313341ca1a3074beab46a8f6919146";
	public static final String PATH_OF_HUNDRED_TRACKS_CSV="/Users/rajaramanin/Documents/workspace/LastFM/tracks.csv";
	public static final String PATH_OF_SIMILAR_TRACKS_ID_CSV="/Users/rajaramanin/Documents/workspace/LastFM/track_id_sim_track_id.csv";
	public static final String CSV_SEPARATOR = ",";
	public static final String PROJECT_NAME_FOR_LAST_FM = "Java/1.6.0.4";
	public static final String CHARACTER_ENCODING = "UTF-8";
	public static final int TRACKS_FETCH_COUNT = 100;
	public static final int TRACKS_FETCH_SUB_COUNT = 10;
	public static final String ARTIST_TO_FETCH = "cher";
	public static final String TRACK_NAME_TO_FETCH = "believe";
	public static final String TRACK_NAME_XPATH = "lfm/similartracks/track/name";
	public static final String TRACK_MBID_XPATH = "lfm/similartracks/track/mbid";
	public static final String TRACK_ARTIST_XPATH = "lfm/similartracks/track/artist/name";
	//public static final String TRACK_ARTIST_MBID_XPATH = "lfm/similartracks/track/artist/mbid";
	public static final String LAST_FM_URL = "http://ws.audioscrobbler.com/2.0/?method=track.getsimilar&artist=";
	
	
	public static final String USER_INFO_URL = "http://ws.audioscrobbler.com/2.0/?method=user.getinfo&user=";
	public static final String USER_RECENT_TRACKS_URL = "http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=";
	public static final String LIMIT = "&limit=200";
	public static final int MAX_PAGES = 6;
	public static final String FROM_DATE ="&from=1293840000";
	public static final int NUM_OF_MILLI_SECS_FOR_ONE_API_CALL = 320;
	public static final String VALID_USERS_ID_FILE = "/Users/yskuo/Desktop/Poloisawesome/Users/users.txt";
	public static final String FILE_VALID_USERS_ID_FILTERED = "/Users/yskuo/Desktop/Poloisawesome/ValidUsers/users.txt";
	public static final String DIRECTORY_USERS_JSON_VALID = "/Users/yskuo/Desktop/Poloisawesome/ValidUsers/";
	public static final String USERS_JSON_DIRECTORY = "/Users/yskuo/Desktop/Poloisawesome/Users/";
	public static final long USER_ID_START_POSITION = 0;
	public static final long USER_ID_END_POSITION = 35916500;
	public static final int MINIMUM_PLAY_COUNT =50;
	
	public static final String USER_INFO_FILE_EXTENSION = ".json";
	public static final String USER_TRACKS_FILE_EXTENSION = ".json";
	public static final String PADDING_LENGTH = "8";
	public static final int PADDING_LENGTH_PAGE = 5;
	public static final char PADDING_CHARACTER = '0';
	public static final String PAGE_EXTENSION =".json";
}
