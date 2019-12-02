package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.Datum;

public class CsvParkingReader implements ParkingReader {
	
	protected String filename;
	
	public CsvParkingReader(String parkingFilename) {
		filename = parkingFilename;
	}
	
	@Override
	public List<Datum> getFileContents() {
		
		List<Datum> data = new ArrayList<Datum>();
	
		Scanner in = null;
	
		try {
			
			in = new Scanner(new File(filename));
			in.useDelimiter(",");
			
			while (in.hasNextLine()) {
				String nextLine = in.nextLine();
				String[] nextLineArray = nextLine.split(",");
				Datum d = new Datum();
				d.addKeyValuePair("state", nextLineArray[0]);
				d.addKeyValuePair("longitude", nextLineArray[1]);
				d.addKeyValuePair("latitude", nextLineArray[2]);
				if (in.hasNextLine()) {
					in.nextLine(); // consume the rest of the line
				}
				data.add(d);	
			}
			
		} catch (Exception e) {
			
			throw new IllegalStateException(e);
			
		} finally {
			
			in.close();
			
		}
		
		return data;
	}	

}
