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
			//System.out.println("Total amount of records: " + records.size()); //TODO delete after testing
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
	 * This method accesses the web logs in records and returns an ArrayList of Strings of 
	 * unique IP addresses that had access on the given day. 
	 * @param someday
	 * @return ArrayList IP addresses
	 */
	public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
		ArrayList<String> ipVisitsOnDay = new ArrayList<String>();
		for(LogEntry log : records) {
			String s = log.getAccessTime().toString();
			//System.out.println(s); //TODO remove after testing
			if(s.contains(someday)) {
				//System.out.println("date found!"); //TODO replace with storage code
				if(!ipVisitsOnDay.contains(log.getIpAddress())) {
					ipVisitsOnDay.add(log.getIpAddress());
				}
			}
		}
		return ipVisitsOnDay; 
	}
	
	/**
	 * This method returns the number of unique IP addresses in records that have a status code
	 *  in the range from low to high, inclusive
	 * @param low
	 * @param high
	 * @return int for the amount of unique IP addresses within range
	 */
	public int countUniqueIPsInRange(int low, int high) {
		ArrayList<String> ipAddresses = new ArrayList<String>();
		for(LogEntry log : records) {
			if(log.getStatusCode()>= low && log.getStatusCode()<= high) {
				if(!ipAddresses.contains(log.getIpAddress())) {
					ipAddresses.add(log.getIpAddress());
				}
			}
		}
		return ipAddresses.size();
	}
}
