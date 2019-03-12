package main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Singleton Enum-based implementation of Log
// Note this will need to be called with Log.INSTANCE

public enum Log {

	INSTANCE;
	
	// File destination
	private StringBuilder logFile;
	
	// Private constructor
	private Log() {
		logFile = new StringBuilder();
	}
	
	// Synchorized public method for files to a StringBuilder object.
	public synchronized void log(String s) {
		// Attempt to write to given file
		logFile.append(s);
		logFile.append(System.lineSeparator());
		
	}
	
	// method that prints StringBuilder to a file.
	public void logToFile() {
		try {
			FileWriter fw = new FileWriter( new File("LogFile.txt"), false);
			fw.write(logFile.toString());
			fw.close();
		} catch (IOException e) {
			System.out.println(e.toString());
			
		}
	}
}
