package edu.upenn.cit594.datamanagement;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Datum;

public class JsonParkingReader implements ParkingReader {
	
	protected String filename;
	protected List<Datum> data;

	public JsonParkingReader(String parkingFilename) {
		filename = parkingFilename;
		data = new ArrayList<Datum>();
	}
	
	private void validateAndAdd(JSONObject d, Set<String> keys) {
		
		// use the "get" method to print the value associated with that key
		String text = (String) d.get("text");
		
		if (text.toLowerCase().contains("flu ") ||
			text.toLowerCase().contains("flu.") ||
			text.toLowerCase().contains("flu!") ||
			text.toLowerCase().contains("flu?")) {
			Datum entry = new Datum();
			
			for (String k : keys) {
				entry.addKeyValuePair(k, d.get(k));
			}
			
			data.add(entry);

		}
	}

	@Override
	public List<Datum> getFileContents() {
		
		// create a parser
		JSONParser parser = new JSONParser();

		// check if Tweets File exists or not
		FileReader frTweets = null;
		try {
			frTweets = new FileReader(filename);
		} catch (IOException e) {
			System.out.println("The program encoutered an error with the tweets file.");
			System.exit(0);
		}
		
		// get the array of JSON objects
		JSONArray tweets = null;
		try {
			tweets = (JSONArray) parser.parse(frTweets);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// use an iterator to iterate over each element of the array
		Iterator iter = tweets.iterator();
		
		// Get first tweet
		JSONObject tweet = (JSONObject) iter.next();
		
		// Get set of keys from first tweet
		Set<String> keys = tweet.keySet();
		
		validateAndAdd(tweet, keys);

		// iterate while there are more objects in array
		while (iter.hasNext()) {
			// get the next JSON object
			tweet = (JSONObject) iter.next();
			
			validateAndAdd(tweet, keys);
		}
		
		try {
			frTweets.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
		
	}

}
