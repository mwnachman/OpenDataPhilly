package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import edu.upenn.cit594.data.Property;

public class PropertyReader {
	
	String inputFilename;
	
	public PropertyReader(String propertiesFilename) {
		inputFilename = propertiesFilename;
	}
	
	/**
	 * This method retrieves file contents for specified property file
	 * @return
	 */
	public ArrayList<Property> getFileContents() {
		ArrayList<Property> outList = new ArrayList<Property>();
		
		// Check that file exists
		File file = new File(inputFilename);
		if (file.canRead()) {
			try {
				Scanner s = new Scanner(file);
				String titles = s.nextLine(); // get first line
				String[] titleArray = titles.split(",");
				
				String[] titlesOfInterest = {"market_value","total_livable_area","zip_code"};
				HashMap<String, Integer> titleLocations = new HashMap<String, Integer>();
				int n = 0; // counter 
				
				for (String tempString : titleArray) {
					for (int i = 0; i < 3; i++) {
						if (tempString.equals(titlesOfInterest[i])) {
							titleLocations.put(tempString, n); // add locations to HashMap for storage
						}
					}
					n++;
				}
				
				int mvLocation = titleLocations.get("market_value");
				int tlaLocation = titleLocations.get("total_livable_area");
				int zcLocation = titleLocations.get("zip_code");
				
				
				while (s.hasNextLine()) {
					String tempString = s.nextLine();
					String[] tempArray = tempString.split(",");
					
					try {
						double mv = Double.parseDouble(tempArray[mvLocation]);
						double tla = Double.parseDouble(tempArray[tlaLocation]);
						long zc = Long.parseLong(tempArray[zcLocation].substring(0, 5));
						
						Property tempProperty = new Property(mv,tla,zc);
						outList.add(tempProperty);
			
					} catch (Exception e) {
						
					}
				
				}
				
				return outList;
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("The properties file name you entered as an argument cannot be read/does not exist! Edit and rerun program!");
			System.exit(0);
		}
		
		
		return null;
		
	}

}
