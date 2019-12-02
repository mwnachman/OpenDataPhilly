package edu.upenn.cit594.ui;

import edu.upenn.cit594.processor.ParkingProcessor;
import sun.security.ssl.Debug;
import edu.upenn.cit594.logging.Logger;
import java.util.Scanner;

public class Presentation {

	protected ParkingProcessor processor;

	public Presentation(ParkingProcessor p) {
		processor = p;
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
