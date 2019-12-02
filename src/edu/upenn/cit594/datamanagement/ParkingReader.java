package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Datum;

public interface ParkingReader {
	
	public List<Datum> getFileContents();
	
}