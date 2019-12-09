package edu.upenn.cit594.ui;

import edu.upenn.cit594.processor.LivableAreaAttribute;
import edu.upenn.cit594.processor.MarketValueAttribute;
import edu.upenn.cit594.processor.ParkingProcessor;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.PropertyProcessor;
//import sun.security.ssl.Debug;
import edu.upenn.cit594.logging.Logger;

import java.util.Map;
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

		Scanner s = new Scanner(System.in);
		
		while (lcheck) {

			System.out.println("What action would you like to take? (0-6):");

			try {
				String userSelection = s.next();

				// Add Selection to Log
				logger.log(System.currentTimeMillis() + " " + userSelection);

				int intUserSelection = Integer.parseInt(userSelection);

				// Run code based on input
				switch (intUserSelection) {
				case 0:
					System.exit(0);
					break;

				case 1:
					int totalPopulation = populationProcessor.getTotalPopulation();
					System.out.println(totalPopulation + "\n");
					break;

				case 2:
					Map<Integer, Float> finesPerZipCode = parkingProcessor.calculateFinesPerZipCode();
					for (Map.Entry zipTotal : finesPerZipCode.entrySet()) {
						Integer zip = (Integer) zipTotal.getKey();
						Integer population = populationProcessor.getPopulationSize(zip);
						Float zipTotalAmount = (Float) zipTotal.getValue();
						Float perCapitaTotal = zipTotalAmount / population;
						if (zipTotalAmount != 0 && population != 0) {							
							System.out.println(zipTotal.getKey() + " " + (String.format("%#.4f", perCapitaTotal)));
						}
					}
					System.out.println("\n");
					break;

				case 3:
					System.out.println("For which zip code would you like to show the average market value? ");
					try {
						int zipCodeInput = s.nextInt();
						System.out.println(propertyProcessor.getAverageValue(new MarketValueAttribute(), zipCodeInput));
					} catch (Exception e) {
						System.out.println("0\n"); // if not a proper zip code
					}
					break;

				case 4:
					System.out.println("For which zip code would you like to show the average livable area? ");
					try {
						int zipCodeInput = s.nextInt();
						System.out.println(propertyProcessor.getAverageValue(new LivableAreaAttribute(), zipCodeInput));
					} catch (Exception e) {
						System.out.println("0\n"); // if not a proper zip code
					}
					break;

				case 5:
					System.out.println(
						"For which zip code would you like to show the total residential market value per capita? ");
					try {
						int zipCodeInput = s.nextInt();
						System.out.println(propertyProcessor.totalResidentialMarketValuePerCapita(zipCodeInput) + "\n"); // display
																															// output
					} catch (Exception e) {
						System.out.println("0\n");
					}
					break;

				case 6:
					Map<Integer, Float> violationsPer100KPropertyValue = parkingProcessor.calculateInspectionViolationsPer100KPropertyValuePerCapita(propertyProcessor);
					for (Map.Entry entry : violationsPer100KPropertyValue.entrySet()) {
						System.out.println(entry.getKey() + " " + (String.format("%#.1f", entry.getValue())));
					}
					System.out.println("\n");
					break;

				default:
					System.out.println("You entered an invalid input! Enter an integer 0-6!");

				}
			} catch (Exception e) {
				System.out.println("There was an error.  Please enter an integer 0-6. " + e);
			}

		}

		s.close();

	}

}
