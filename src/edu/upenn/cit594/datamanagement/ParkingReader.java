package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.ParkingTicket;

public abstract class ParkingReader {
	
	protected String filename;
	protected List<ParkingTicket> data;
	protected String[] keys = {"date", "fine", "violation", "plate_id", "state", "ticket_number", "zip_code"};
		
	public List<ParkingTicket> getFileContents() {
		return null;
	}
	
	public String getFilename() {
		return filename;
	}
	
	private void validateAndAdd(Object o) {};
	
}