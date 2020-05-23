/**
 * 
 */
package week3;

import java.util.*;

import edu.duke.*;

/**
 * @author Leon
 * @version 1.0
 */
public class LogAnalyzer {
	private ArrayList<LogEntry> records; 
	
	public LogAnalyzer() {
		records = new ArrayList<LogEntry>();
	}
	
	public void readFile(String filename) {
		FileResource file = new FileResource(filename);
		for(String line : file.lines()) {
			LogEntry record = WebLogParser.parseEntry(line);
			records.add(record);
			System.out.println("Total amount of records: " + records.size()); //TODO delete after testing
		}
	}

	public void printAll() {
		for(LogEntry le : records) {
			System.out.println(le);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
