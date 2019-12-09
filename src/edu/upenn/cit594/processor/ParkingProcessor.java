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
	
	private Map<Integer, Integer> calculateExpiredInspectionsPerZipCode() {
		String searchedViolation = "EXPIRED INSPECTION";
		
		if (results.containsKey("expiredInspectionsPerZipCode")) {
			return (Map<Integer, Integer>) results.get("expiredInspectionsPerZipCode");
		}
		
		if (parkingData == null) {
			// only create it when we know we need it
			parkingData = parkingReader.getFileContents();
		}
		
		Map<Integer, Integer> expiredInspectionsPerZipCode = new TreeMap<Integer, Integer>();
		
		for (ParkingTicket ticket : parkingData) {
			Integer zip = (int) ticket.getZip();
			String state = ticket.getState();
			String violation = ticket.getViolation();
			if (violation.toUpperCase().equals(searchedViolation) && state.equals("PA")) {
				if (expiredInspectionsPerZipCode.containsKey(zip)) {
					Integer total = expiredInspectionsPerZipCode.get(zip);
					int newTotal = total + 1;
					expiredInspectionsPerZipCode.replace(zip, newTotal);
				} else {
					expiredInspectionsPerZipCode.put(zip, 1);
				}
			}
		}
		
		results.put("expiredInspectionsPerZipCode", expiredInspectionsPerZipCode);
		
		return expiredInspectionsPerZipCode;
	}
	
	public Map<Integer, Float> calculateInspectionViolationsPer100KPropertyValuePerCapita(PropertyProcessor propP) {
		if (results.containsKey("inspectionViolationsPer100K")) {
			return (Map<Integer, Float>) results.get("inspectionViolationsPer100K");
		}
		
		Map<Integer, Integer> expiredInspections = calculateExpiredInspectionsPerZipCode();
		Map<Integer, Float> expiredInspectionsPer100KPropertyValue = new TreeMap<Integer, Float>();
		
		for (Map.Entry zipData : expiredInspections.entrySet()) {
			Integer zip = (Integer) zipData.getKey();
			int violations = (int) zipData.getValue();
			int perCapitaPropVal = propP.totalResidentialMarketValuePerCapita(zip);
			float zip100KPerCapita = perCapitaPropVal / 100000F;
			if (violations != 0) {
				float violationsPer100K = violations / zip100KPerCapita;
				expiredInspectionsPer100KPropertyValue.put(zip, violationsPer100K);
			}
		}

		results.put("inspectionViolationsPer100K", expiredInspectionsPer100KPropertyValue);
		return expiredInspectionsPer100KPropertyValue;

	}
	
	public List<ParkingTicket> getParkingData() {
		return parkingData;
	}

}
