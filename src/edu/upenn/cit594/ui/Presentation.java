package edu.upenn.cit594.ui;

import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
import sun.security.ssl.Debug;
import edu.upenn.cit594.logging.Logger;
import java.util.Scanner;

public class Presentation {

	protected ParkingProcessor parkingProcessor;
	protected PropertyProcessor propertyProcessor;
	protected PopulationProcessor populationProcessor;

	public Presentation(ParkingProcessor p1, PropertyProcessor p2, PopulationProcessor p3) {
		parkingProcessor = p1;
		propertyProcessor = p2;
		populationProcessor = p3;
	}
	
	public void start() {
		
		Logger logger = Logger.getInstance();
				
//		System.out.println("Please check " + logger.getFilename() + " for the results!");
		
		// Create User Interaction Interface //
		
//		System.out.println("Which action would you like to perform (0-6)?:");
//		Scanner s = new Scanner(System.in);
//		System.out.println(s.nextLine());
		
		
		
	}
	
}
