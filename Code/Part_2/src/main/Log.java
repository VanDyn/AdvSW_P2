package main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Singleton Enum-based implementation of Log
// Note this will need to be called with Log.INSTANCE

public enum Log {

	INSTANCE;
	
	// File destination
	private File logfile;
	
	// Private constructor
	private Log() {
		logfile = new File("LogFile.txt");
	}
	
	// Synchorized public method for writing to log.
	public synchronized void log(String s) {
		// Attempt to write to given file
		try {
			FileWriter fw = new FileWriter(this.logfile,true);
			fw.write(s);
			fw.write(System.lineSeparator());
			fw.close();
		} catch (IOException ex) {
			System.err.println("Could not log " + s);
		}
		
	}
}
