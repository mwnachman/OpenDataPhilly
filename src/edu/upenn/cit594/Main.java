package edu.upenn.cit594;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

//import edu.penn.cit594.processor.Processor;
import edu.upenn.cit594.ui.Presentation;
import edu.upenn.cit594.datamanagement.JsonParkingReader;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
import edu.upenn.cit594.datamanagement.CsvParkingReader;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		if (args.length != 5) {
			System.out.println("The user did not submit the correct number of arguments.");
			System.exit(0);
		}
		String fileType = args[0];
		if (!fileType.equals("csv") && !fileType.equals("json")) {
			System.out.println("The user did not submit an accepted format.");
			System.exit(0);
		}
		
		String parkingFilename = args[1];
		
		ParkingReader parkingReader = fileType.equals("json") ? new JsonParkingReader(parkingFilename) : new CsvParkingReader(parkingFilename);
		System.out.println("parking Reader " + parkingReader);
		
		String propertiesFilename = args[2];
		String populationFilename = args[3];
		PropertyReader propertyReader = new PropertyReader(propertiesFilename);
		PopulationReader populationReader = new PopulationReader(populationFilename);
		
		ParkingProcessor p1 = new ParkingProcessor(parkingReader);
		PropertyProcessor p2 = new PropertyProcessor(propertyReader);
		PopulationProcessor p3 = new PopulationProcessor(populationReader);
		
		
		String loggerFilename = args[4];
		Logger.setFilename(loggerFilename);
		
		Presentation ui = new Presentation(p1, p2, p3);
		ui.start();

	}
}
