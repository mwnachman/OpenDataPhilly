package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.data.ParkingTicket;
import edu.upenn.cit594.datamanagement.ParkingReader;

public class ParkingProcessor {

	private List<ParkingTicket> parkingData;
	private ParkingReader parkingReader;
	private Map<String, Object> results;

	public ParkingProcessor(ParkingReader pr) {
		parkingReader = pr;
		results = new HashMap<String, Object>();
	}

	public Map<Integer, Float> calculateFinesPerZipCode() {
		if (results.containsKey("finesPerZipCode")) {
			return (TreeMap<Integer, Float>) results.get("finesPerZipCode");
		}
		
		if (parkingData == null) {
			// only create it when we know we need it
			parkingData = parkingReader.getFileContents();
		}
		
		Map<Integer, Float> finesPerZipCode = new TreeMap<Integer, Float>();

		for (ParkingTicket ticket : parkingData) {
			Integer zip = (int) ticket.getZip();
			Float fine = ticket.getFine();
			String state = ticket.getState();
			if (state.equals("PA")) {
				if (finesPerZipCode.containsKey(zip)) {
					Float total = finesPerZipCode.get(zip);
					Float newTotal = total + fine;
					finesPerZipCode.replace(zip, newTotal);
				} else {
					finesPerZipCode.put(zip, fine);
				}
			}
		}
		
		results.put("finesPerZipCode", finesPerZipCode);
		
		return finesPerZipCode;
	}
	
	public List<ParkingTicket> getParkingData() {
		return parkingData;
	}

}
