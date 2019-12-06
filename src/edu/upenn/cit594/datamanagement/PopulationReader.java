package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import edu.upenn.cit594.logging.Logger;

public class PopulationReader {

	String inputFilename;

	public PopulationReader(String populationFilename) {
		this.inputFilename = populationFilename;
	}

	public HashMap<Long, Long> getFileContents() {

		// Log Opening File //
		Logger l = Logger.getInstance();
		l.log(System.currentTimeMillis() + " " + inputFilename);

		HashMap<Long, Long> outMap = new HashMap<Long, Long>();
		File file = new File(inputFilename);

		if (file.canRead()) {
			try {
				Scanner s = new Scanner(file);

				while (s.hasNextLine()) {
					String tempString = s.nextLine();
					String[] tempArray = tempString.split(" ");

					try {
						long zipCode = Long.parseLong(tempArray[0]);
						long population = Long.parseLong(tempArray[1]);

						outMap.put(zipCode, population);

					} catch (Exception e) {

					}

				}
				
				s.close();
				return outMap;

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println(
					"The population file name you entered as an argument cannot be read/does not exist! Edit and rerun program!");
			System.exit(0);
		}

		return null;
	}

}
