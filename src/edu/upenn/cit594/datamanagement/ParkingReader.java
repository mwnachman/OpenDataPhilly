package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.Datum;

public abstract class ParkingReader {
	
	protected String filename;
	protected List<Datum> data;
	protected String[] keys = {"date", "fine", "violation", "plate_id", "state", "ticket_number", "zip_code"};
		
	public List<Datum> getFileContents() {
		return null;
	}
	
	public String getFilename() {
		return filename;
	}
	
	private void validateAndAdd(Object o) {};
	
}