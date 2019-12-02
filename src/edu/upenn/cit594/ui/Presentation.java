package edu.upenn.cit594.ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.logging.Logger;

public class Presentation {

	protected ParkingProcessor processor;

	public Presentation(ParkingProcessor p) {
		processor = p;
	}
	
	public void start() {
//		TreeMap<String, ArrayList<String>> mapOfTweets = processor.doStateAnalysis();
		
		Logger logger = Logger.getInstance();
				
//		for (Map.Entry<String, ArrayList<String>> entry : mapOfTweets.entrySet()) {
//			for (int i = 0; i < entry.getValue().size(); i++) {
//				logger.log(entry.getKey() + "\t" + entry.getValue().get(i));
//			}
//		}
		
		logger.log("\n");
		
//		for (Map.Entry<String, ArrayList<String>> entry : mapOfTweets.entrySet()) {
//			logger.log(entry.getKey() + ": " + entry.getValue().size());
//		}
		
		System.out.println("Please check log.txt for the flu results!");
		
	}
	
}
