package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

/**
 * This class serves to specify MarketValue for the Strategy Pattern in 3/4
 * @author davidlarkin
 *
 */
public class MarketValueAttribute implements SumStrategy {

	@Override
	public double getSpecifiedValue(Property p) {
		return p.getMarketValue();
	}

	@Override
	public String getStrategyType() {
		return "MarketValue";
	}
	
	

}
