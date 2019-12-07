package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.upenn.cit594.logging.Logger;

public class PopulationReader {

	String inputFilename;

	public PopulationReader(String populationFilename) {
		this.inputFilename = populationFilename;
	}

	public Map<Integer, Integer> getFileContents() {

		// Log Opening File //
		Logger l = Logger.getInstance();
		l.log(System.currentTimeMillis() + " " + inputFilename);

		Map<Integer, Integer> outMap = new HashMap<Integer, Integer>();
		File file = new File(inputFilename);

		if (file.canRead()) {
			try {
				Scanner s = new Scanner(file);

				while (s.hasNextLine()) {
					String tempString = s.nextLine();
					String[] tempArray = tempString.split(" ");

					try {
						int zipCode = Integer.parseInt(tempArray[0]);
						int population = Integer.parseInt(tempArray[1]);

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
