/**
 * 
 */
package week3;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import edu.duke.*;

/**
 * @author Leon
 *
 */
public class Tester {

	/**
	 * Contains tests for the LogEntry Class
	 */
	private void testLogEntry() {
		LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 
				200, 500);
		System.out.println(le);
	}
	
	/**
	 * Contains tests for the LogAnalyzer Class
	 */
	private void testLogAnalyzer() {
		LogAnalyzer lA = new LogAnalyzer();
		//lA.readFile("D:\\Users\\Leon\\Skydrive\\Development\\JPALSD\\data\\Week3\\short-test_log");// for Desktop
		lA.readFile("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week3\\weblog3-short_log");// for VAIO
		//lA.readFile("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week3\\weblog1_log");// for VAIO
		//lA.readFile("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week3\\short-test_log");// for VAIO
		//lA.printAll();
		System.out.println("Total of unique IP addresses: " + lA.countUniqueIPs());
		lA.printAllHigherThanNumber(400);
		String date = "Mar 17";//Change according to requested date
		ArrayList<String> visits = lA.uniqueIPVisitsOnDay(date);
		System.out.println(visits.size() + " unique visits on " + date);
		int low = 300;
		int high = 399;
		System.out.println("Amount of unique IP adresses with statuscode between " + low 
				+ " and " + high + " is " + lA.countUniqueIPsInRange(low, high));
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		counts = lA.countVisitsPerIP();
		for(String ipAddress : counts.keySet()) {
			if(counts.get(ipAddress) > 0) {
				System.out.println("IPAddress " + ipAddress + " occured " + counts.get(ipAddress) + 
					" times.");
			}
		}
		System.out.println("Most visits for an IPaddress is " + lA.mostNumberVisitsByIP(counts));
		ArrayList<String> addresses = lA.iPsMostVisits(counts);
		for(String s : addresses) {
			System.out.println("IP address: " + s);
		}
	}
	
	public void testIPForDays() {
		LogAnalyzer lA = new LogAnalyzer();
		//lA.readFile("D:\\Users\\Leon\\Skydrive\\Development\\JPALSD\\data\\Week3\\short-test_log");// for Desktop
		//lA.readFile("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week3\\weblog3-short_log");// for VAIO
		lA.readFile("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week3\\weblog1_log");// for VAIO
		//lA.readFile("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week3\\short-test_log");// for VAIO
		HashMap<String, ArrayList<String>> ipPerDay = lA.iPForDays();
		for(String s : ipPerDay.keySet()) {
			System.out.println("date " + s + " IP Address");
			ArrayList<String> iPs = ipPerDay.get(s);
			for(String ip : iPs) {
				System.out.println("\t" +  ip);
			}
		}
		String dayWithMostVisits = lA.dayWithMostIPVisits(ipPerDay);
		System.out.println("Day with most IP visits " + dayWithMostVisits);
		String day = "Mar 17";
		ArrayList<String> ipMostVistOnDay = lA.iPsWithMostVisitsOnDay(ipPerDay, day);
		System.out.println("ips most visited from on day :" + day);
		for(String s : ipMostVistOnDay) {
			System.out.println(s);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tester test = new Tester();
		//test.testLogEntry();
		//test.testLogAnalyzer();
		test.testIPForDays();
	}

}
