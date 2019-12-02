package edu.upenn.cit594.ui;

import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.logging.Logger;

public class Presentation {

	protected ParkingProcessor processor;

	public Presentation(ParkingProcessor p) {
		processor = p;
	}
	
	public void start() {
		
		Logger logger = Logger.getInstance();
				
//		System.out.println("Please check " + logger.getFilename() + " for the results!");
		
	}
	
}
