package edu.upenn.cit594.logging;

import java.io.File;
import java.io.PrintWriter;

public class Logger {
	
	private static PrintWriter out;
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
			out = new PrintWriter(new File(loggerFilename));
		} catch (Exception e) {}
	}
	
	// Non-static method
	public void log(String msg){
		out.println(msg);
		out.flush();
	}

}
