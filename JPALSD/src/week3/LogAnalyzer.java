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
	private HashMap<Integer, String> dateMap;
	
	public LogAnalyzer() {
		records = new ArrayList<LogEntry>();
		dateMap = new HashMap<Integer, String>();
		dateMap.put(0, "Jan"); dateMap.put(1, "Feb"); dateMap.put(2, "Mar"); dateMap.put(3, "Apr");
		dateMap.put(4, "May"); dateMap.put(5, "Jun"); dateMap.put(6, "Jul"); dateMap.put(7, "Aug");
		dateMap.put(8, "Sep"); dateMap.put(9, "Oct"); dateMap.put(10, "Nov"); dateMap.put(11, "Dec");
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
	
	/**
	 * This method checks for each LogEntry if the ipAddress already exists in the HashMap,
	 * if it exists, add count 1 to existing count. If not, add ipAddress to map and set
	 * count to 1.
	 * @return Hashmap with ipAddresses and counts
	 */
	public HashMap<String, Integer> countVisitsPerIP() {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for(LogEntry log : records) {
			String ipAddress = log.getIpAddress();
			if(counts.containsKey(ipAddress)) {
				counts.put(ipAddress, counts.get(ipAddress) + 1);
			}
			else {
				counts.put(ipAddress, 1);
			}
		}
		return counts;
	}
	
	/**
	 * This method maps an IP address to the number of times that IP address appears in the web log file. 
	 * It returns the maximum number of visits to this website by a single IP address
	 * @param ipAddress
	 * @return number
	 */
	public int mostNumberVisitsByIP(HashMap<String, Integer> ipAddress) {
		int maxVisits = 0;
		for(String s : ipAddress.keySet()) {
			if(ipAddress.get(s) > maxVisits) {
				maxVisits = ipAddress.get(s);
			}
		}
		return maxVisits;
	}
	
	/**
	 * This method returns an ArrayList of Strings of IP addresses that all have the maximum number 
	 * of visits to this website
	 */
	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipAddress) {
		ArrayList<String> addresses = new ArrayList<String>();
		int maxVisits = this.mostNumberVisitsByIP(ipAddress);
		for(String s : ipAddress.keySet()) {
			if(ipAddress.get(s) == maxVisits) {
				addresses.add(s);
			}
		}
		return addresses;
	}
	
	/**
	 * This method returns a HashMap<String, ArrayList<String>> that uses records 
	 * and maps days from web logs to an ArrayList of IP addresses that occurred 
	 * on that day (including repeated IP addresses)
	 * @return HashMap with key day and collection of IP numbers
	 */
	public HashMap<String, ArrayList<String>> iPForDays() {
		HashMap<String, ArrayList<String>> ipsPerDay = new HashMap<String, ArrayList<String>>();
		for(LogEntry log : records) {//for each record
			Date date = log.getAccessTime();//get date
			//create date string Mmm dd
			//System.out.println(date); //TODO Delete after testing
			String dateReturn = createDateString(date);
			String iPnumber = log.getIpAddress();
			if(!ipsPerDay.containsKey(dateReturn)) {//check if date exists in ipsPerDay
				ArrayList<String> ipNumbers = new ArrayList<String>();//if does not exist, create new Arraylist ipNumbers
				ipNumbers.add(iPnumber);//add ipaddres to ipNumbers
				ipsPerDay.put(dateReturn, ipNumbers);//add new <date>,ipNumbers to ipsPerDay
			}
			else {
				ArrayList<String> ipNumbers = ipsPerDay.get(dateReturn);
				ipNumbers.add(iPnumber); //add ipaddress to existing ArrayList<String>
				ipsPerDay.put(dateReturn, ipNumbers);
			}
		}	
		return ipsPerDay;
	}
	
	/**
	 * . This method returns the day that has the most IP address visits. If there is a tie, then return any such day
	 * @param ipAddressesPerDay
	 * @return
	 */
	public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipAddressesPerDay) {
		String day = "";
		int max = 0;
		for(String ipAddress : ipAddressesPerDay.keySet()) {
			if(ipAddressesPerDay.get(ipAddress).size() > max) {
				max = ipAddressesPerDay.get(ipAddress).size();
				day = ipAddress;
			}
		}
		return day;
	}
	
	/**
	 * This helper method turns a date object into a date String in the format "Mmm dd" 
	 * @param date
	 * @return String date for menthod iPForDays
	 */
	public String createDateString(Date date) {
		@SuppressWarnings("deprecation")
		int month = date.getMonth();
		int day = date.getDate();
		String dateReturn = dateMap.get(month) + " " + day;
		//System.out.println(dateReturn); //TODO delete after testing
		return dateReturn;
	}
}
