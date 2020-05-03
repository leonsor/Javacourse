/**
 * To try the examples from the videos
 */
package week3;
import edu.duke.*;

import java.io.File;

import org.apache.commons.csv.*;

/**
 * @author Leon
 *
 */
public class TryOut {

	/**
	 * Method to create a CSV parser from a single file
	 * @return a CSV Parser
	 */
	public CSVParser getFile() {
		FileResource file = new FileResource();
		CSVParser parser = file.getCSVParser();
		return parser;
	}
	
	/**
	 * Method to iterate over the records with weather data per day stored in a file
	 * Find the record with the highest temperature in the day 
	 * @param parser
	 * @return the record line with the highest temperature on that day 
	 */
	public CSVRecord hottestHourInFile(CSVParser parser) {
		CSVRecord largestSoFar = null;
		for(CSVRecord record : parser) {
			largestSoFar = getLargestOfTwo(largestSoFar, record);
		}
		return largestSoFar;
	}
	
	/**
	 * Test Method for hottest Hour in file
	 */
	public void testHottestInDay() {
		CSVParser parser = getFile();
		CSVRecord answer = hottestHourInFile(parser);
		System.out.println("hottest temperature was: " + answer.get(1) + " at " + answer.get(0));
	}

	/**
	 * Method to find the highest temperature 
	 * whilst iterate over multiple files (containing one day per hour) 
	 * @return CSVRecord with hottest temperature
	 */
	public CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			FileResource file = new FileResource(f);
			CSVParser parser = file.getCSVParser();
			CSVRecord currentRecord = hottestHourInFile(parser);
			largestSoFar = getLargestOfTwo(largestSoFar, currentRecord);
		}
		return largestSoFar; 
	}

	/**
	 * Method to compare two records of a temperature data file and return the highest temperature record
	 * @param largestSoFar
	 * @param currentRecord
	 * @return CSVRecord with highest temperature
	 */
	public CSVRecord getLargestOfTwo(CSVRecord largestSoFar, CSVRecord currentRecord) {
		if(largestSoFar == null) {
			largestSoFar = currentRecord;
		}
		else {
			if(Double.parseDouble(currentRecord.get(1)) > Double.parseDouble(largestSoFar.get(1))) {
				largestSoFar = currentRecord;
			}
		}
		return largestSoFar;
	}
	
	
	public void testHottestInManyDays() {
		CSVRecord answer = hottestInManyDays();
		System.out.println("hottest temperature was: " + answer.get(1) + " at " + answer.get(13));
	}
	
	/**
	 * Main method to test TryOut Java methods
	 * @param args
	 */
	public static void main(String[] args) {
		TryOut tryOut = new TryOut();
		// tryOut.testHottestInDay(); // To test hottest temperature in one day
		tryOut.testHottestInManyDays(); // To test hottest temperature in multiple days
	}

	
}
