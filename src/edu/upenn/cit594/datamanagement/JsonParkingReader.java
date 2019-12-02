package edu.upenn.cit594.datamanagement;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Datum;

public class JsonParkingReader extends ParkingReader {

	public JsonParkingReader(String parkingFilename) {
		filename = parkingFilename;
		data = new ArrayList<Datum>();
	}
	
	private void validateAndAdd(JSONObject d) {
		
		Datum entry = new Datum();
		
		for (String k : keys) {
			if (!d.get(k).equals("")) {
				entry.addKeyValuePair(k, d.get(k));
			} else {
				return;
			}
		}

		data.add(entry);

	}

	@Override
	public List<Datum> getFileContents() {
		// create a parser
		JSONParser parser = new JSONParser();

		// check if Parking Data File exists or not
		FileReader parkingData = null;
		try {
			parkingData = new FileReader(filename);
		} catch (IOException e) {
			System.out.println("The program encoutered an error with the parking file. " + e);
			System.exit(0);
		}
		
		// get the array of JSON objects
		JSONArray tickets = null;
		try {
			tickets = (JSONArray) parser.parse(parkingData);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// use an iterator to iterate over each element of the array
		Iterator iter = tickets.iterator();
		
		// Get first ticket
		JSONObject ticket = (JSONObject) iter.next();
		validateAndAdd(ticket);

		// iterate while there are more objects in array
		while (iter.hasNext()) {
			// get the next JSON object
			ticket = (JSONObject) iter.next();
			
			validateAndAdd(ticket);
		}
		
		try {
			parkingData.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
		
	}

}
