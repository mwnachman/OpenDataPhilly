package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.ParkingTicket;
import edu.upenn.cit594.datamanagement.ParkingReader;

public class ParkingProcessor {
	
	protected List<ParkingTicket> parkingData;
	
	public ParkingProcessor(ParkingReader parkingReader) {
		
		parkingData = parkingReader.getFileContents();
		
	}

}
