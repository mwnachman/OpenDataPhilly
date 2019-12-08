package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.datamanagement.PopulationReader;

public class PopulationProcessor {
	
	Map<Integer, Integer> populationData;
	Map<String, Object> results;
	
	public PopulationProcessor(PopulationReader populationReader) {
		this.populationData = populationReader.getFileContents();
		results = new HashMap<String, Object>();
	}
	
	/**
	 * This method returns the size of the population of a zip code.
	 * @param zipCode
	 * @return
	 */
	public int getPopulationSize(int zipCode) {
		
		if (populationData.containsKey(zipCode)) {
			return populationData.get(zipCode);
		} else {
			return 0;
		}

	}
	
	public int getTotalPopulation() {
		if (results.containsKey("totalPopulation")) {
			return (int) results.get("totalPopulation");
		}
		
		int totalPopulation = 0;
		
		for (Map.Entry mapElement : populationData.entrySet()) {
			totalPopulation += (int) mapElement.getValue();
		}

		results.put("totalPopulation", totalPopulation);
		
		return totalPopulation;
	}
	

}
