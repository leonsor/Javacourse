/**
 * 
 */
package week3;

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
		lA.readFile("D:\\Users\\Leon\\Skydrive\\Development\\JPALSD\\data\\Week3\\short-test_log");
		lA.printAll();
		System.out.println("Total of unique IP addresses: " + lA.countUniqueIPs());
		lA.printAllHigherThanNumber(200);
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
