/**
 * Assignment week 3 - Weather data
 */
package week3;

import java.io.File;
import org.apache.commons.csv.*;
import edu.duke.*;

/**
 * @author Leon
 *
 */
public class Assignment {
	
	/**
	 * Method to determine the coldest temperature in a CSV File with
	 * temperatures per hour for one day
	 * @param parser
	 * @return CSV Record of hour with lowest temperature
	 */
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldestSoFar = null;
		for(CSVRecord currentHour : parser) {
			coldestSoFar = getColdestOfTwo(coldestSoFar, currentHour);
		}
	return coldestSoFar; 
	}

	/**
	 * Helper method to calculate the CSVRecord with the lowest temperature
	 * @param parser
	 * @param coldestHour
	 * @return CSVRecord with coldest temperature
	 */
	public CSVRecord getColdestOfTwo(CSVRecord coldestSoFar, CSVRecord currentHour) {
		if(coldestSoFar == null) {
			coldestSoFar = currentHour;
		}
		else { 
			double currentTemp = Double.parseDouble(currentHour.get(1));
			double lowestTemp = Double.parseDouble(coldestSoFar.get(1));
			if(currentTemp != -9999 && (currentTemp < lowestTemp)) {
				coldestSoFar = currentHour;
			}
		}
		return coldestSoFar;	
		}
	
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
	 * Test method for coldestHourInFile()
	 * read file, check coldest hour and print  
	 */
	public void testColdestHourInFile() {
		CSVParser parser = getFile();
		CSVRecord answer = coldestHourInFile(parser);
		System.out.println("lowest temperature was: " + answer.get(1) + " at " + answer.get(0));
	}
	
	/**
	 * Method to find the coldest temperature in multiple files
	 * Furthermore print different data
	 * @return file name of file with lowest temperatures (why???)
	 */
	public String fileWithColdestTemperature() {
		CSVRecord coldestSoFar = null;
		File searchFile = null;
		DirectoryResource files = new DirectoryResource();
		for(File f : files.selectedFiles()) {
			FileResource file = new FileResource(f);
			CSVParser parser = file.getCSVParser();
			CSVRecord currentRecord = coldestHourInFile(parser);
			//coldestHourInMany = getColdestOfTwo(coldestHourInMany, currentRecord);
			if(coldestSoFar == null) {
				coldestSoFar = currentRecord;
				searchFile = f;
			}
			else { 
				double currentTemp = Double.parseDouble(currentRecord.get(1));
				double lowestTemp = Double.parseDouble(coldestSoFar.get(1));
				if(currentTemp != -9999 && (currentTemp < lowestTemp)) {
					coldestSoFar = currentRecord;
					searchFile = f;
				}
			}
		}
		System.out.println("Coldest day was in file " + searchFile.getName());
		System.out.println("Coldest temperature on that day was: " + coldestSoFar.get(1));
		System.out.println("All the temperatures on the coldest day were: ");
		FileResource endFile = new FileResource(searchFile);
		CSVParser endParser = endFile.getCSVParser();
		for(CSVRecord record : endParser) {
			System.out.println(record.get(13) + " " + record.get(1));
		}
		return searchFile.getName(); 
	}
	
	public void testFileWithColdestTemperature() {
		String filename = fileWithColdestTemperature();
		System.out.println("Records printed from file: " + filename);
	}
	
	/**
	 * Method to determine the lowest humidity in a file with weather data per hour
	 * @param parser
	 * @return the record with the lowest humidity
	 */
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestHumidity = null;
		for(CSVRecord record : parser) {
			String recordHumidity = record.get(3);
			if(recordHumidity.equals("N/A")== false) {
				lowestHumidity = getLowestOfTwo(lowestHumidity, record);
			}
		}
		return lowestHumidity; //TODO change into real return value
	}

	/**
	 * @param lowestHumidity
	 * @param record
	 * @return CSVRecord with lowest Humidity
	 */
	public CSVRecord getLowestOfTwo(CSVRecord lowestHumidity, CSVRecord record) {
		if(lowestHumidity == null) {
			lowestHumidity = record;
		}
		else {
			if(Integer.parseInt(record.get(3)) < Integer.parseInt(lowestHumidity.get(3))) {
				lowestHumidity = record;
			}
		}
		return lowestHumidity;
	}
	
	/**
	 * Method lowestHumidityInManyFiles
	 * uses the lowestHumidityInFile to determine the lowest humidity row per file,
	 * compares the lowest in multiple files 
	 * @return lowest record in multiple files
	 */
	public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord lowestHumidity = null;
		DirectoryResource dr = new DirectoryResource();
		for(File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file);
			CSVParser parser = fr.getCSVParser();
			if(lowestHumidity == null) {
				lowestHumidity = lowestHumidityInFile(parser);
			}
			else {
				CSVRecord currentRecord = lowestHumidityInFile(parser);
				lowestHumidity = getLowestOfTwo(lowestHumidity, currentRecord);
			}
		}
		return lowestHumidity;
	}
	
	/**
	 * Test Method for lowestHumidityInFile
	 */
	public void testLowestHumidityInFile() {
		FileResource f = new FileResource();
		CSVParser parser = f.getCSVParser();
		CSVRecord record = lowestHumidityInFile(parser);
		System.out.println("Lowest humidity was " + record.get(3) + " at " + record.get(13));
	}
	
	/**
	 * Test method for lowestHumidityInManyFiles
	 */
	public void testLowestHumidityInManyFiles() {
	CSVRecord lowestHumidity = lowestHumidityInManyFiles();
	System.out.println("Lowest Humidity was " + lowestHumidity.get(3) + " at " + lowestHumidity.get(13));;
	}
	
	/**
	 * Method averageTemperatureInFile to read all records and determine average temp on that day
	 * @param parser
	 * @return double average temperature
	 */
	public double averageTemperatureInFile(CSVParser parser) {
		double sumTemp = 0.0;  //declare sum of all temperatures
		int numberOfRecords = 0; // declare number of records
		for(CSVRecord currentRecord : parser) { //for each record in file
			double currentTemp = Double.parseDouble(currentRecord.get(1)); // get temperature per hour
			if(currentTemp != -9999) { // check for invalid temperature -9999
				sumTemp += currentTemp; //add current temp to sume
				numberOfRecords += 1; //add one to counter numberOfRecored
			}
		}
		return (sumTemp / numberOfRecords); // return sum of temperatures devided by amount of records used
	}
	
	/**
	 * Test Method for averageTemperatureInFile
	 */
	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
	}
	
	/**
	 * Method to 
	 * @param parser (of the file)
	 * @param value (search humidity)
	 * @return double average temperature where humidity is >= value 
	 */
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double sumTemp = 0.0;  //declare sum of all temperatures
		int numberOfRecords = 0; // declare number of records
		for(CSVRecord currentRecord : parser) { //for each record in file
			String currentHumidity = currentRecord.get(3);
			if((currentHumidity.equals("N/A")== false) && (Integer.parseInt(currentHumidity) >= value)) { 
				// Check for N/A (no humidity available) and humidity >= the search value
				double currentTemp = Double.parseDouble(currentRecord.get(1)); // get temperature per hour
				if(currentTemp != -9999) { // check for invalid temperature -9999
					sumTemp += currentTemp; //add current temp to sume
					numberOfRecords += 1; //add one to counter numberOfRecored
				}
			}
		}
		if(numberOfRecords == 0) { //if no records with humidity >= value exist
			return 0.0;
		}
		return (sumTemp / numberOfRecords); // return sum of temperatures devided by amount of records used
	}
	
	/**
	 * test method for av Temp with High Humidity in File. If return value is 0.0, no valid temperatures
	 * are found, else print the average temperature 
	 */
	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double averageTemp = this.averageTemperatureWithHighHumidityInFile(parser, 80);
		if(averageTemp == 0.0) {
			System.out.println("No temperatures with that humidity");
		}
		else {
			System.out.println("Average Temp when High humidity is " + averageTemp);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Assignment as = new Assignment();
		//as.testColdestHourInFile();
		as.testFileWithColdestTemperature();
		//as.testLowestHumidityInFile();
		//as.testLowestHumidityInManyFiles();
		//as.testAverageTemperatureInFile();
		//as.testAverageTemperatureWithHighHumidityInFile();
	}

}
