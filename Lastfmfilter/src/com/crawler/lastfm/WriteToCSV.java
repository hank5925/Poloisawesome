package com.crawler.lastfm;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.List;

public class WriteToCSV {

	public static void writeSimilarTracksMbidTracksToCSV(
			HashSet<SimilarTracks> hashset, String filePath) throws Exception {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath),
					Constants.CHARACTER_ENCODING));
			for (SimilarTracks simTrk : hashset) {
				StringBuffer oneLine = new StringBuffer();
				oneLine.append(simTrk.getParentMbid());
				oneLine.append(Constants.CSV_SEPARATOR);
				oneLine.append(simTrk.getChildMbid());
				bw.write(oneLine.toString());
				bw.newLine();
			}
			bw.flush();
			bw.close();
	}

	public static void writeTracksToCSV(List<Track> tracks, String filePath) throws Exception {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath),
					Constants.CHARACTER_ENCODING));
			Track track = null;
			for (int i = 0; i < tracks.size(); i++) {
				track = tracks.get(i);
				if (track.getTrackmbid() != null) {
					StringBuffer oneLine = new StringBuffer();
					oneLine.append(track.getTrackmbid());
					oneLine.append(Constants.CSV_SEPARATOR);
					oneLine.append("\"" + track.getTrackName() + "\"");
					oneLine.append(Constants.CSV_SEPARATOR);
					oneLine.append("\"" + track.getArtistName() + "\"");
					bw.write(oneLine.toString());
					bw.newLine();
				}

			}
			bw.flush();
			bw.close();
		
	}
}
