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
		
		boolean lcheck = true;

		while (lcheck) {

			System.out.println("What action do you want to take? (0-6):");

			Scanner s = new Scanner(System.in);

			try {
				int userSelection = s.nextInt();

				// Check that is between 0-6
				if (userSelection < 0 || userSelection > 6) {
					System.out.println(
							"You entered a wrong input! Program terminated! Restart and enter and integer 0-6!");
				} else { // Run code based on input
					switch (userSelection) {
					case 0:
						System.exit(0);
						break;
					case 1:
						
						break;
					case 2:
						
						break;
					case 3:
						
						break;
					case 4:

						break;
					case 5:
						System.out.println(
								"What is the zip code you want to show the total residential market value per capita for?:");
						try {
							long zipCodeInput = s.nextLong();
							System.out.println(propertyProcessor.totalResidentialMarketValuePerCapita(zipCodeInput)); // display output
						} catch (Exception e) {
							System.out.println("0");
						}
						break;
					case 6:
						
						break;
					}

				}
			} catch (Exception e) {
				System.out.println("You entered a wrong input! Program terminated! Restart and enter and integer 0-6!");
			}

		}

	}

}
