/**
 * 
 */
package week3;

import java.util.ArrayList;
import java.util.Date;
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
		//lA.readFile("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week3\\weblog-short_log");// for VAIO
		lA.readFile("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week3\\weblog1_log");// for VAIO
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
			
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tester test = new Tester();
		//test.testLogEntry();
		test.testLogAnalyzer();
	}

}
