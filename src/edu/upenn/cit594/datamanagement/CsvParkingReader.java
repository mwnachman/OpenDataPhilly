package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.ParkingTicket;
import edu.upenn.cit594.logging.Logger;

public class CsvParkingReader extends ParkingReader {

	public CsvParkingReader(String parkingFilename) {
		filename = parkingFilename;
		data = new ArrayList<ParkingTicket>();
	}

	private void validateAndAdd(String[] d) {

		ParkingTicket entry = new ParkingTicket();
		int i = 0;

		for (String k : keys) {

			if (d.length > i && !d[i].equals("")) {
				entry.addKeyValuePair(k, d[i]);
				i++;
			} else {
				return;
			}
		}

		data.add(entry);

	}

	@Override
	public List<ParkingTicket> getFileContents() {

		// Log Opening File //
		Logger l = Logger.getInstance();
		l.log(System.currentTimeMillis() + " " + filename);

		Scanner in = null;

		try {

			in = new Scanner(new File(filename));
			in.useDelimiter(",");

			while (in.hasNextLine()) {
				String nextLine = in.nextLine();
				String[] nextLineArray = nextLine.split(",");
				validateAndAdd(nextLineArray);
			}

		} catch (Exception e) {

			throw new IllegalStateException(e);

		} finally {

			in.close();

		}

		return data;
	}

}
