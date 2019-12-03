package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationReader;

public class PopulationProcessor {
	
	HashMap<Long, Long> populationData;
	
	public PopulationProcessor(PopulationReader populationReader) {
		this.populationData = populationReader.getFileContents();
	}
	
	/**
	 * This method returns the size of the population of a zip code.
	 * @param zipCode
	 * @return
	 */
	public long getPopulationSize(long zipCode) {
		
		if (populationData.containsKey(zipCode)) {
			return populationData.get(zipCode);
		} else {
			return 0;
		}

	}
	

}
