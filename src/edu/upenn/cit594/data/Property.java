package edu.upenn.cit594.data;

public class Property {
	
	float marketValue;
	float totalLivableArea;
	int zipCode;
	
	public Property(float marketValue, float totalLivableArea, int zc) {
		this.marketValue = marketValue;
		this.totalLivableArea = totalLivableArea;
		this.zipCode = zc;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public double getTotalLivableArea() {
		return totalLivableArea;
	}

	public long getZipCode() {
		return zipCode;
	}

}
