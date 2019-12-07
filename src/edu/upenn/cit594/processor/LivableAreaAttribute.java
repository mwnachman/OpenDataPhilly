package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

/**
 * This class serves to specify LivableArea for the Strategy Pattern in 3/4
 * @author davidlarkin
 *
 */
public class LivableAreaAttribute implements SumStrategy {

	@Override
	public double getSpecifiedValue(Property p) {
		return p.getTotalLivableArea();
	}

	@Override
	public String getStrategyType() {
		return "LivableArea";
	}

}
