package edu.upenn.cit594.processor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.data.ParkingTicket;
import edu.upenn.cit594.datamanagement.ParkingReader;

public class ParkingProcessor {

	protected List<ParkingTicket> parkingData;
	protected ParkingReader parkingReader;

	public ParkingProcessor(ParkingReader pr) {
		parkingReader = pr;
	}

	public Map<Integer, Float> calculateFinesPerZipCode() {
		if (parkingData == null) {
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

		return finesPerZipCode;
	}

}
