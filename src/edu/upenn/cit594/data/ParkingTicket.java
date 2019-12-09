package edu.upenn.cit594.data;

import java.util.HashMap;
import java.util.Map;

public class ParkingTicket {

	Map<String, Object> ticket;
	
	public ParkingTicket() {
		ticket = new HashMap<String, Object>();
	}

	public void addKeyValuePair(String key, Object value) {
		ticket.put(key, value);
	}

	public String getValue(String key) {
		return ticket.get(key).toString();
	}

	public float getFine() {
		return Float.parseFloat((String) ticket.get("fine"));
	}

	public int getZip() {
		return Integer.parseInt((String) ticket.get("zip_code"));
	}

	public String getState() {
		return (String) ticket.get("state");
	}
	
	public String getViolation() {
		return (String) ticket.get("violation");
	}

}
