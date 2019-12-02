package edu.upenn.cit594.data;

import java.util.HashMap;
import java.util.Map;

public class Datum {

	Map<String, Object> datum;
	
	public Datum() {
		datum = new HashMap<String, Object>();
	}
	
	public void addKeyValuePair(String key, Object value) {
		datum.put(key, value);
	}
	
	public String getValue(String key) {
		return datum.get(key).toString();
	}
}
