/**
 * 
 */
package week3;
import java.util.*;

/**
 * @author Leon
 * - Testclass for the Lecture Wk 3
 * @version 1.0
 */
public class LogEntry {
	private String ipAddress;
	private Date accessTime;
	private String request;
	private int statusCode;
	private int bytesReturned;
	
	public LogEntry(String ipAddress, Date accessTime, String request, int statusCode, int bytesReturned) {
		this.ipAddress = ipAddress;
		this.accessTime = accessTime;
		this.request = request;
		this.statusCode = statusCode;
		this.bytesReturned = bytesReturned;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	
	public Date getAccessTime() {
		return accessTime;
	}
	
	public String getRequest() {
		return request;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public int getBytesReturned() {
		return bytesReturned;
	}
	
	public String toString() {
		return ipAddress + " " + accessTime + " " + request + " " + 
				statusCode + " " + bytesReturned;
	}
	
	public void tester() {
		//TODO
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//TODO - currently tested in Tester.java
		/*LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 
				200, 500);
		System.out.println(le);*/

	}

}
