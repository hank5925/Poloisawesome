package com.crawler.lastfm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class IpStreamToFile {
	
	public static Object getJavaObjFromStream(InputStream is, Class classVar) throws Exception{
		Reader reader = new InputStreamReader(is);
	    JsonElement elem = new JsonParser().parse(reader);
	    Gson gson  = new GsonBuilder().create();
	    Object obj = gson.fromJson(elem, classVar);
	    if(is!=null){
	    	is.close();
	    }
	    return obj;
	    
	}
	
	//Save input stream in to file
	public static void saveRecords(String completeFileName, InputStream inputStream) {
		File targetFile = new File(completeFileName);
		File parent = targetFile.getParentFile();
		//System.out.println(completeFileName);
		if(!parent.exists() && !parent.mkdirs()){
			//System.out.println("-----------");
		    //throw new IllegalStateException("Couldn't create dir: " + parent);
			targetFile.getParentFile().mkdirs();
		}
		OutputStream outputStream = null;

		try {
			// write the inputStream to a FileOutputStream
			outputStream = new FileOutputStream(new File(completeFileName));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		
		//System.out.println("Saved");
	}
}