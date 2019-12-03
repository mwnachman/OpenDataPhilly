package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;

import edu.upenn.cit594.data.Datum;
import edu.upenn.cit594.datamanagement.ParkingReader;

public class ParkingProcessor {
	
	protected List<Datum> parkingData;
	
	public ParkingProcessor(ParkingReader parkingReader) {
		
		parkingData = parkingReader.getFileContents();
		
	}

}
