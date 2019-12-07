package edu.upenn.cit594.data;

public class Property {
	
	double marketValue;
	double totalLivableArea;
	long zipCode;
	
	public Property(double marketValue, double totalLivableArea, long zc) {
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
