package edu.upenn.cit594.processor;


import edu.upenn.cit594.data.Property;

/**
 * This is the general interface that serves as a strategy for summing up values
 * based on specified attribute
 * 
 * @author davidlarkin
 *
 */
public interface SumStrategy {

	public double getSpecifiedValue(Property p);
	
	public String getStrategyType();

}
