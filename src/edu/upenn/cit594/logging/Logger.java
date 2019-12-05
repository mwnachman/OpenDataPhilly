package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileWriter;

public class Logger {
	
	private static FileWriter fw;
	private static String filename;
	
	// Singleton logger instance
	private static Logger instance = new Logger();
	
	// Singleton accessor method - allows other classes to access the singleton
	public static Logger getInstance() {
		return instance;
	}
	
	public static void setFilename(String loggerFilename) {
		filename = loggerFilename;
		try {
			fw = new FileWriter(new File(loggerFilename),true); // make is so it can append
		} catch (Exception e) {}
	}
	
	
	public String getFilename() {
		return filename;
	}
	
	// Non-static method
	public void log(String msg){
		try {
			fw.write(msg+"\n");
			fw.flush();
		} catch (Exception e) {
			
		}
	}

}
