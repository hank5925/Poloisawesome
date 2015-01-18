package com.crawler.lastfm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;

public class FileOperations {
	// Append the UserId that is valid in the user.txt file
	public static void addValidUsersinList(String text,String fileName) {
		PrintWriter out = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName, true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.println(text);
		} catch (IOException e) {
			// File writing/opening failed at some stage.
		} finally {
			try {
				if (out != null) {
					out.close(); // Will close bw and fw too
				} else if (bw != null) {
					bw.close(); // Will close fw too
				} else if (fw != null) {
					fw.close();
				} else {
					// Oh boy did it fail hard! :3
				}
			} catch (IOException e) {
				// Closing the file writers failed for some obscure reason
			}
		}
	}
	// Copy The Input Stream
	//IS can be read once so copy it while getting user info
	//check if user details are valid if so get the is again from copy and save it
	public static byte[] copyStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		org.apache.commons.io.IOUtils.copy(is, baos);
		byte[] bytes = baos.toByteArray();
		return bytes;

	}
	// Find the Last UserId saved to start the process from there
	public static long getlastvalidUserFromFile() {

		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(Constants.VALID_USERS_ID_FILE));
			String lastUser="";
			while ((sCurrentLine = br.readLine()) != null) {
				lastUser= sCurrentLine;
			}
			System.out.println("Found the Valid id from users file.. returning the last value of UserId stored in the file "+lastUser);
			return Long.parseLong(lastUser);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("Didnt find the Valid id from users file.. returning the least value of UserId Defined in constant "+Constants.USER_ID_START_POSITION);
		return Constants.USER_ID_START_POSITION;
	}
	
	public static List<String> readFile(String fileName) {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				list.add(sCurrentLine);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static void copyFolder(File src, File dest) throws IOException {

		if (src.isDirectory()) {

			// if directory not exists, create it
			if (!dest.exists()) {
				dest.mkdir();
				System.out.println("Directory copied from " + src + "  to "
						+ dest);
			}

			// list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				copyFolder(srcFile, destFile);
			}

		} else {
			// if file, then copy it
			// Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			//System.out.println("File copied from " + src + " to " + dest);
		}
	}

}
