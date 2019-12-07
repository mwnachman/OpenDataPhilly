package edu.upenn.cit594.processor;

import java.util.Map;

import edu.upenn.cit594.datamanagement.PopulationReader;

public class PopulationProcessor {
	
	Map<Integer, Integer> populationData;
	
	public PopulationProcessor(PopulationReader populationReader) {
		this.populationData = populationReader.getFileContents();
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
		int totalPopulation = 0;
		
		for (Map.Entry mapElement : populationData.entrySet()) {
			totalPopulation += (int) mapElement.getValue();
		}
			
		return totalPopulation;
	}
	

}
