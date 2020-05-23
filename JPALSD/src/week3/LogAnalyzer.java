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
	
	/**
	 * This method takes the ip-address from each entry, checks if it is already in the 
	 * ipAddresses ArrayList. If not already in there, add to the list.
	 * @return amount of unique IPs
	 */
	public int countUniqueIPs() {
		ArrayList<String> ipNumbers = new ArrayList<String>();
		for(LogEntry log : records) {
			String ipNumber = log.getIpAddress();
			if(!ipNumbers.contains(ipNumber)) {
				ipNumbers.add(ipNumber);
			}
		}
		return ipNumbers.size(); 
	}

	public void printAll() {
		for(LogEntry le : records) {
			System.out.println(le);
		}
	}
	
	public void printAllHigherThanNumber(int num) {
		ArrayList<LogEntry> logError = new ArrayList<LogEntry>();
		for(LogEntry log : records) {
			int statusCode = log.getStatusCode();
			if(statusCode > num) {
				logError.add(log);
			}
		}
		if(logError.size() == 0) {
			System.out.println("No logs found with status code > " + num);
		}
		else {
			System.out.println("Printing log entries with status code > " + num);
			for(LogEntry error : logError) {
				System.out.println(error);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
