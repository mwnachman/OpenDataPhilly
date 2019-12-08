package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.data.ParkingTicket;

public interface ParkingInterface {
	
	public List<ParkingTicket> getFileContents();
	void validateAndAdd(Object o);;
	
}
