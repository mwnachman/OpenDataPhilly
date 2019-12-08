package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.HashMap;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class PropertyProcessor {

	private PropertyReader propertyReader;
	private ArrayList<Property> propertyData;
	private PopulationProcessor popProcessor;
	private HashMap<Integer, Integer> marketValueMemoization = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> livableAreaMemoization = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> totalResidentialMarketValue = new HashMap<Integer, Integer>();

	public PropertyProcessor(PropertyReader pr, PopulationProcessor populationProcessor) {
		this.propertyReader = pr;
		popProcessor = populationProcessor;
	}

	/**
	 * This method calculates the Total Residential Market Value Per Capita
	 * 
	 * @param zipCode
	 * @return
	 */
	public int totalResidentialMarketValuePerCapita(int zipCode) {
		if (this.propertyData == null) {
			// only create it when we know we need it
			this.propertyData = propertyReader.getFileContents();
		}
		
		// Check Memoization Structure for Previous Input
		boolean lcheck = this.checkIfAlreadyCalculated(totalResidentialMarketValue, zipCode);
		if (lcheck) {
			return this.getPreviousCalculationValue(totalResidentialMarketValue, zipCode);
		}
		
		// If not previously calculated then proceed:

		// NEED TO VERIFY ZIP CODE //
		double marketValue = 0;

		for (Property p : propertyData) {
			if (p.getZipCode() == zipCode) {
				marketValue = marketValue + p.getMarketValue();
			}
		}

		// Get Zip Code Population //
		int popSize = popProcessor.getPopulationSize(zipCode);

		if (popSize == 0 || marketValue == 0) {
			return 0;
		}

		// Calculate Out Value
		int outValue = (int) (marketValue / popSize);
		
		totalResidentialMarketValue.put(zipCode,outValue); // Add to Memoization Structure
		
		return outValue;
	}

	/**
	 * This is the method that can be used for parts 3/4 of this assignment. It
	 * takes in the SumStartegy class and the zipcode and returns average
	 * MarketValue or LivableArea depending on the SumStrategy used. For
	 * MarketValue, the Sum Strategy that should be used is MarketValueAttribute
	 * class and for LivableArea, the LivableAreaAttribute class should be used.
	 * 
	 * @param s
	 * @param zipCode
	 * @return
	 */
	public int getAverageValue(SumStrategy s, int zipCode) {
		if (this.propertyData == null) {
			// only create it when we know we need it
			this.propertyData = propertyReader.getFileContents();
		}
		
		// Check Memoization
		if (s.getStrategyType() == "MarketValue") {
			boolean lcheck = this.checkIfAlreadyCalculated(marketValueMemoization, zipCode);
			if (lcheck) {
				return this.getPreviousCalculationValue(marketValueMemoization, zipCode);
			}
		} else if (s.getStrategyType() == "LivableArea") {
			boolean lcheck = this.checkIfAlreadyCalculated(livableAreaMemoization, zipCode);
			if (lcheck) {
				return this.getPreviousCalculationValue(livableAreaMemoization, zipCode);
			}
		}

		// If not already calcualted then calculate
		double sumValue = 0;
		int count = 0;
		for (Property p : propertyData) {
			if (p.getZipCode() == zipCode) {
				sumValue = sumValue + s.getSpecifiedValue(p);
				count++;
			}
		}

		int outValue = 0; // value to return

		if (count != 0) {
			outValue = (int) (sumValue / count); // calculation
		}

		// Add To Memoization Structure
		if (s.getStrategyType() == "MarketValue") {
			marketValueMemoization.put(zipCode, outValue);
		} else if (s.getStrategyType() == "LivableArea") {
			livableAreaMemoization.put(zipCode, outValue);
		}

		return outValue;

	}

	private boolean checkIfAlreadyCalculated(HashMap<Integer, Integer> memoizationStructure, int zipCode) {
		if (memoizationStructure.isEmpty()) {
			return false; // if hash map is empty
		} else {
			return memoizationStructure.containsKey(zipCode);
		}
	}

	private int getPreviousCalculationValue(HashMap<Integer, Integer> memoizationStructure, int zipCode) {
		return memoizationStructure.get(zipCode);
	}

}
