package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class PropertyProcessor {
	
	
	private ArrayList<Property> propertyData;
	private PopulationProcessor popProcessor;
	
	public PropertyProcessor(PropertyReader propertyReader, PopulationProcessor populationProcessor) {
		this.propertyData = propertyReader.getFileContents();
		this.popProcessor = populationProcessor;
	}
	
	/**
	 * This method calculates the Total Residential Market Value Per Capita
	 * @param zipCode
	 * @return
	 */
	public int totalResidentialMarketValuePerCapita(long zipCode) {
		
		// NEED TO VERIFY ZIP CODE //
		double marketValue = 0;
		
		for (Property p : propertyData) {
			if (p.getZipCode() == zipCode) {
				marketValue = marketValue + p.getMarketValue();
			}
		}
		
		// Get Zip Code Population //
		long popSize = popProcessor.getPopulationSize(zipCode);
		
		if (popSize == 0 || marketValue == 0) {
			return 0;
		}

		return (int) (marketValue / popSize);
	}

}
