package week3;
import org.apache.commons.csv.*;
import edu.duke.FileResource;

/**
 * 
 */

/**
 * @author Leon
 *
 */
public class Part1 {
	
	/**
	 * Program to search for a country and return the 3 records for that country, or NOT FOUND
	 * @param parser
	 * @param country
	 */
	public void countryInfo(CSVParser parser, String country) {
		boolean found = false;
		for(CSVRecord record : parser) { //for each record
			// check whether Country == country
			if(record.get(0).contains(country)) { 
				String foundCountry = record.get(0); //("Country");
				String foundExports = record.get(1); //("Exports");
				String foundValue = record.get(2); //("Value (dollars)");
				System.out.println(foundCountry + ": " + foundExports + ": " + foundValue);
				//print Country, exports, export value
				found = true;
				return;
			}
		}
		if(!found) {
			System.out.println("NOT FOUND");
		}
	}
	
	/**
	 * Program to search for countries which export both items and print them
	 * @param parser
	 * @param exportItem1
	 * @param exportItem2
	 */
	public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		int countriesFound = 0; //TODO test purpose only
		for(CSVRecord record : parser) {
			if(record.get(1).contains(exportItem1) && record.get(1).contains(exportItem2)) {
				System.out.println(record.get(0));
				countriesFound += 1;
			}
		}
		System.out.println("Total amount of countries found: " + countriesFound); //TODO test purpose only
	}
	
	public int numberOfExporters(CSVParser parser, String exportItem) {
		int numberOfExport = 0;
		for(CSVRecord record : parser) {
			if(record.get(1).contains(exportItem)) {
				numberOfExport += 1;
			}
		}
		return numberOfExport;
	}
	
	public void bigExporters(CSVParser parser, String amount) {
		long intAmount = deleteCommas(amount); //parse String amount into long 
		for(CSVRecord record : parser) {
			String getAmount = record.get(2);
			long intGetAmount = deleteCommas(getAmount); //parse String getAmount into long
			// if(GetAmount.length() > amount.length()) // replaced by method below
			if(intGetAmount > intAmount) { //compare amounts
				System.out.println(record.get(0) + " " + record.get(2));
			}
//			System.out.println("Lenght of getAmount: " + getAmount.length() + " , Lenght of amount: " + amount.length()); //TODO delete after testing
		}
	}
	
	/**
	 * Extra program to convert a String with numbers separated by "," into a long
	 * @param amount
	 * @return
	 */
	public long deleteCommas(String amount) {
		//int amountInt = 0;
		String amountInt = "";
		int index = 0;
		while(index < amount.length()) {
			char c = amount.charAt(index);
			if(c != '$' && c != ',') {
				amountInt += c;
//				System.out.println("current string is: " + amountInt); //TODO test only!
			}
			index += 1;
		}		
		return Long.parseLong(amountInt);
	}
	/**
	 * Standard program to open a file and create a CSVParser
	 * @return CSVParser parser
	 */
	public void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		/* test countryInfo() task 2
		String country1 = "Germany"; //should return Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000
		//String country2 = "Poland"; //should return Poland: machinery and transport equipment 37.8%, intermediate manufactured goods 23.7%, miscellaneous manufactured goods 17.1%, food and live animals 7.6% (2012 est.): $218,900,000,000
		//String country3 = "Timbuktu"; // should return NOT FOUND */
		//String country1 = "Nauru";
		//countryInfo(parser, country1);
		//test listExportersTwoProducts task 3 
		//String export1 = "cotton"; String export2 = "flowers";
		//listExportersTwoProducts(parser, export1, export2);
		/* test numberOfExporters */
		//String exportItem = "coffee"; //output 4 or 42
		//String exportItem = "gold"; //output3 or 30
		//String exportItem = "cocoa";
		//System.out.println(numberOfExporters(parser, exportItem));
		/* test bigExporters */
		String value = "$999,999,999,999";
		bigExporters(parser, value);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Part1 p1 = new Part1();
		p1.tester();
	}

}
