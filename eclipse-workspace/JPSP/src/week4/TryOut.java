/**
 * TryOut Class for following the weeks' examples
 */
package week4;

import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * @author Leon
 *
 */
public class TryOut {
	
/**
 * Method to print all names in the file	
 */
public void printNames() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			int amount = 100;
			if(Integer.parseInt(record.get(2)) <= amount) {
				System.out.println("Name " + record.get(0) + 
						" gender " + record.get(1) + 
						" appeared " + record.get(2) +
						" times this year");
			}
		}
	}

	public void totalBirths(FileResource fr) {
		int totalBorn = 0;
		int totalMale = 0;
		int totalFemale = 0;
		for(CSVRecord record : fr.getCSVParser(false)) {
			int amountBorn = Integer.parseInt(record.get(2));
			totalBorn += amountBorn;
			if(record.get(1).equalsIgnoreCase("f")) {
				totalFemale += amountBorn;
			}
			else {
				totalMale += amountBorn;
			}
		}
		System.out.println("Total amount of births: " + totalBorn);
		System.out.println("Total amount of female births: " + totalFemale);
		System.out.println("Total amount of male births: " + totalMale);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TryOut tr = new TryOut();
		//tr.printNames();
		FileResource file = new FileResource();
		tr.totalBirths(file);
	}

}
