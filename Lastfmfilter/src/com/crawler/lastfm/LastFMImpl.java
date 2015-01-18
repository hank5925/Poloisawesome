package com.crawler.lastfm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class LastFMImpl {

	public static void fetchTracksAndWriteToCSV() throws Exception {

		Document doc = LastFMImpl.readXmlFromInputStream(fetchSimilarTracks(
				Constants.ARTIST_TO_FETCH, Constants.TRACK_NAME_TO_FETCH));
		List<Track> list = new ArrayList<Track>(Constants.TRACKS_FETCH_COUNT);
		LastFMImpl.setObjects(doc, list, Constants.TRACKS_FETCH_COUNT);
		WriteToCSV.writeTracksToCSV(list, Constants.PATH_OF_HUNDRED_TRACKS_CSV);
		WriteToCSV.writeSimilarTracksMbidTracksToCSV(
				processSubTracks(list, Constants.TRACKS_FETCH_SUB_COUNT),
				Constants.PATH_OF_SIMILAR_TRACKS_ID_CSV);

	}

	public static void main(String[] args) {
		try {
			System.out.println("Started");
			LastFMImpl.fetchTracksAndWriteToCSV();
			System.out.println("Completed!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * This function calls the lastfm to get the similar subtracks & stores the
	 * mbid of subtracks in SimilarTracks object
	 */
	public static HashSet<SimilarTracks> processSubTracks(
			List<Track> tracksList, int maxCount) throws Exception {
		HashSet<SimilarTracks> hashset = new HashSet<SimilarTracks>(500); // tracks
		long difference = 0;
		InputStream is = null;
		for (Track trk : tracksList) {
			is = null;
			int timeTowaitForHalfSecondToComplete = (int) (Constants.NUM_OF_MILLI_SECS_FOR_ONE_API_CALL - difference);
			if (timeTowaitForHalfSecondToComplete <= 0) {
				timeTowaitForHalfSecondToComplete = 10;
			}
			Thread.sleep(timeTowaitForHalfSecondToComplete);

			List<Track> similarTracks = new ArrayList<Track>(
					Constants.TRACKS_FETCH_SUB_COUNT);
			try {
				is = fetchSimilarTracks(trk.getArtistName(),
						trk.getArtistName());
				long start_time = System.currentTimeMillis();
				similarTracks = setObjects(readXmlFromInputStream(is),
						similarTracks, Constants.TRACKS_FETCH_SUB_COUNT);

				for (Track subTrk : similarTracks) {
					SimilarTracks temp = new SimilarTracks();
					if (trk.getTrackmbid().compareTo(subTrk.getTrackmbid()) < 0) {
						temp.setParentMbid(trk.getTrackmbid());
						temp.setChildMbid(subTrk.getTrackmbid());
					} else {
						temp.setParentMbid(subTrk.getTrackmbid());
						temp.setChildMbid(trk.getTrackmbid());
					}
					hashset.add(temp);
				}
				long end_time = System.currentTimeMillis();
				difference = end_time - start_time;
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		return hashset;
	}

	/*
	 * This function fetches the similar tracks for a track and return the
	 * tracks collection
	 */
	public static InputStream fetchSimilarTracks(String artist,
			String trackOrMbid) throws Exception {
		String urlToHit = Constants.LAST_FM_URL + URLEncoder.encode(artist)
				+ "&track=" + URLEncoder.encode(trackOrMbid) + "&api_key="
				+ Constants.API_KEY;
		System.out.println(Constants.LAST_FM_URL + artist
				+ "&track=" + trackOrMbid + "&api_key="
				+ Constants.API_KEY);
		URL url = new URL(urlToHit);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent",
				Constants.PROJECT_NAME_FOR_LAST_FM);
		Thread.sleep(25);
		return (conn.getInputStream());

	}

	public static Document readXmlFromInputStream(InputStream is)
			throws Exception {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = null;
		builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(is);
		return document;
	}

	public static List<Track> setObjects(Document doc, List<Track> objects,
			int maxCount) throws Exception {
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeListOfTrk = (NodeList) xPath.compile(
				Constants.TRACK_NAME_XPATH).evaluate(doc,
				XPathConstants.NODESET);
		NodeList nodeListOfTrkMbid = (NodeList) xPath.compile(
				Constants.TRACK_MBID_XPATH).evaluate(doc,
				XPathConstants.NODESET);
		NodeList nodeListOfArtist = (NodeList) xPath.compile(
				Constants.TRACK_ARTIST_XPATH).evaluate(doc,
				XPathConstants.NODESET);
		for (int i = 0; i < nodeListOfTrk.getLength() && i < maxCount; i++) {
			Track trk = new Track();
			if (nodeListOfTrkMbid.item(i).getFirstChild() != null) {
				String trkName = nodeListOfTrk.item(i).getFirstChild()
						.getNodeValue();
				String trkMbid = nodeListOfTrkMbid.item(i).getFirstChild()
						.getNodeValue();
				String artistName = nodeListOfArtist.item(i).getFirstChild()
						.getNodeValue();
				trk.setTrackName(trkName);
				trk.setTrackmbid(trkMbid);
				trk.setArtistName(artistName);
				objects.add(trk);
			}

		}
		return objects;
	}
}
