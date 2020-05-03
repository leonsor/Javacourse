/**
 * Miniproject class for week 4
 * Baby names analysis
 */
package week4;

import java.io.File;

import org.apache.commons.csv.*;
import edu.duke.*;

/**
 * @author Leon
 * Overarching class for the miniproject BabyNames
 */
public class BabyNames {

	/**
	 * Method totalNamesByGender
	 * Added method for final quiz!!!
	 * @param gender
	 */
	public void totalNamesByGender(String gender) {
		int totalNames = 0;
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			if(record.get(1).equalsIgnoreCase(gender)) {
				totalNames += 1;
			}
		}
		System.out.println("Total amount of names for gender " + gender + " : " + totalNames);
	}
	
	public void testTotalNamesByGender() {
		//this.totalNamesByGender("F"); // yob1900 = 2225
		this.totalNamesByGender("M"); // yob1905 = 1421
	}
	/**
	 * Method for reading a file with baby names of children born in one year
	 * @param fr FileResource
	 */
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
	 * Helper method to get a parser for a file using the year
	 * use test files or real files
	 * @param year
	 * @return
	 */
	public CSVParser getParser(int year) {
		/*String path = "D:\\Users\\Leon\\Skydrive\\Development\\eclipse-workspace\\JPSP\\Babynames\\us_babynames_test\\yob";
		String file = path + year + "short.csv"; //use for test files*/
		String path = "D:\\Users\\Leon\\Skydrive\\Development\\eclipse-workspace\\JPSP\\Babynames\\us_babynames_by_year\\yob";
		String file = path + year + ".csv"; //use for real files*/
		FileResource fr = new FileResource(file);
		CSVParser parser = fr.getCSVParser(false);
		return parser;
	}

	/**
	 * Find the ranking of a name in a certain year. If name does not exist, return -1
	 * @param year
	 * @param name
	 * @param gender
	 * @return ranking or -1 if name not found
	 */
	public int getRank(int year, String name, String gender) {
		CSVParser parser = getParser(year);
		int femaleRanking = 0;
		int maleRanking = 0;
		int searchRanking = -1;
		for(CSVRecord current : parser) {
			if(current.get(1).equalsIgnoreCase("F")) {
				femaleRanking += 1;
				if(current.get(0).equalsIgnoreCase(name) && current.get(1).equalsIgnoreCase(gender)) {
					searchRanking = femaleRanking;
				}
			}
			else {
				maleRanking += 1;
				if(current.get(0).equalsIgnoreCase(name) && current.get(1).equalsIgnoreCase(gender)) {
					searchRanking = maleRanking;
				}
			}
		}
		return searchRanking;  // the rank in file, -1 if name is not found
	}
	
	/**
	 * Test method for getRank()
	 */
	public void testGetRank() {
		//int rank = getRank(2012, "Ava", "F"); //return 5
		//int rank = getRank(2012, "Mason", "M"); //return 2
		//int rank = getRank(2012, "Mason", "F"); //return -1
		//Graded Quiz
		//int rank = getRank(1960, "Emily", "F"); //return 251
		int rank = getRank(1971, "Frank", "M"); //return 54
		System.out.println("Rank is " + rank); 
	}
		
	/**
	 * Method to get the name at a given rank in a given year, with gender filter
	 * @param year
	 * @param rank
	 * @param gender
	 * @return the name
	 */
	public String getName(int year, int rank, String gender) {
		String name = "NO NAME";
		CSVParser parser = getParser(year);
		int femaleRanking = 0;
		int maleRanking = 0;
		for(CSVRecord current : parser) {
			if(current.get(1).equalsIgnoreCase("F")) {
				femaleRanking += 1;
				if(femaleRanking == rank && current.get(1).equalsIgnoreCase(gender)) {
					name = current.get(0);
				}
			}
			else {
				maleRanking +=1;
				if(maleRanking == rank && current.get(1).equalsIgnoreCase(gender)) {
					name = current.get(0);
				}
			}
		}
		return name;
	}
	
	/**
	 * Test Method for getName()
	 */
	public void testGetName() {
		//int year = 2012;int rank = 2;String gender = "M"; //Mason
		//int year = 2012;int rank = 5;String gender = "F"; //Ava
		//int year = 2012;int rank = 6;String gender = "M"; //NO NAME
		// Graded Quiz
		//int year = 1980;int rank = 350;String gender = "F"; //Mia
		int year = 1982;int rank = 450;String gender = "M"; //Forrest
		System.out.println("Name at rank " + rank + " is " + getName(year, rank, gender));
	}
	
	/**
	 * Method to find a name by ranking in a different year, using the ranking in original yob
	 * @param name
	 * @param year
	 * @param newYear
	 * @param gender
	 * @return the name if found, 
	 */
	public String whatIsNameInYear(String name, int year, int newYear, String gender) {
		String newName = "";
		int currentRank = this.getRank(year, name, gender); //determines rank in current year
		if(currentRank == -1) {
			newName = "Name: " + name + "not known in year " + year;
			return newName;
		}
		newName = getName(newYear, currentRank, gender);
		return newName;
	}
	
	/**
	 * Test method for whatIsNameInYear()
	 */
	public void testWhatIsNameInYear() {
		//String name = "Isabella"; int year = 2012; int newYear = 2014; String gender = "F"; //Sophia
		//String name = "Mason"; int year = 2012; int newYear = 2014; String gender = "M"; //Liam
		//String name = "Leon"; int year = 1962; int newYear = 2014; String gender = "M"; //Sophia
		//Graded Quiz
		//String name = "Susan"; int year = 1972; int newYear = 2014; String gender = "F"; //Addison
		String name = "Owen"; int year = 1974; int newYear = 2014; String gender = "M"; //Leonel
		String newName = this.whatIsNameInYear(name, year, newYear, gender);
		String genText = " she ";
		if(gender.equalsIgnoreCase("M")) {
			genText = " he ";
		}
		System.out.println(name + " born in " + year + " would be " + newName + 
				" if" + genText + "was born in " + newYear);
	}
	
	/**
	 * Method to find the year of highest ranking across multiple files
	 * @param name
	 * @param gender
	 * @return int year or -1 if not found in any of the years selected 
	 */
	public int yearOfHighestRank(String name, String gender) {
		int yearHighest = -1;
		int highestRanking = 0;
		DirectoryResource dr = new DirectoryResource(); //read files to use
		for(File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file); //create file resource
			CSVParser parser = fr.getCSVParser(false); //create parser
			int ranking = getRankInFile(name, gender, parser);
			System.out.println(ranking); //TODO remove after test
			System.out.println("file name " + file.getName()); //TODO remove after test
			if(ranking != -1) { //name does not exist in current year
				if(highestRanking == 0) { //no previous value
					highestRanking = ranking; //set first value and year
					yearHighest = Integer.parseInt(file.getName().substring(3,7));//declare yearHighest with new value
				}
				else { //there was already a value AND there is a ranking
					if(ranking < highestRanking) { //ranking is higher (lower number!)
						highestRanking = ranking; //declare new highestRanking
						yearHighest = Integer.parseInt(file.getName().substring(3,7));//declare yearHighest with new value
					}
				}
			}
			System.out.println("In between results: ranking " + highestRanking + " year: " + yearHighest); //TODO remove after testing
		}
		System.out.println("Year with highest ranking " + yearHighest);
		return yearHighest;
	}
	
	/**
	 *  * Find the ranking of a name in a certain file. If name does not exist, return -1
	 * @param name
	 * @param gender
	 * @return ranking or -1 if name not found
	 */
	public int getRankInFile(String name, String gender, CSVParser parser) {
		int femaleRanking = 0;
		int maleRanking = 0;
		int searchRanking = -1;
		for(CSVRecord current : parser) {
			if(current.get(1).equalsIgnoreCase("F")) {
				femaleRanking += 1;
				if(current.get(0).equalsIgnoreCase(name) && current.get(1).equalsIgnoreCase(gender)) {
					searchRanking = femaleRanking;
				}
			}
			else {
				maleRanking += 1;
				if(current.get(0).equalsIgnoreCase(name) && current.get(1).equalsIgnoreCase(gender)) {
					searchRanking = maleRanking;
				}
			}
		}
		return searchRanking;  // the rank in file, -1 if name is not found
	}
	
	/**
	 * Test method for getRankInFile, used in yearOfHighestRank and getAverageRank 
	 */
	public void testGetRankInFile() {
		String name = "Emma"; String gender = "F"; 
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		System.out.println("Rank for name " + name + " is " + getRankInFile(name, gender, parser));
	}
	
	public void testYearOfHighestRank() {
		//String name = "Emma"; String gender = "F";
		//String name = "Mason"; String gender = "M";
		// Graded Quiz
		//String name = "Genevieve"; String gender = "F"; //1914
		String name = "Mich"; String gender = "M"; //1960
		int yearHighest = yearOfHighestRank(name, gender);
		if(yearHighest == -1) {
			System.out.println("Name " + name + " is not recorded in any of the files");
		}
		else {
			System.out.println("Year of highest ranking for name " + name + " is " + yearHighest);
		}
	}
	
	public double getAverageRank(String name, String gender) {
		int totalRanking = 0;
		int amountOfRanking = 0;
		DirectoryResource dr = new DirectoryResource(); //read files to use
		for(File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file); //create file resource
			CSVParser parser = fr.getCSVParser(false); //create parser
			int ranking = getRankInFile(name, gender, parser);
			System.out.println(ranking); //TODO remove after test
			System.out.println("file name " + file.getName()); //TODO remove after test
			if(ranking != -1) {//name does exist in current year
				totalRanking += ranking; //ad current ranking to total ranking
				amountOfRanking += 1;
				System.out.println("In between results: totalRanking " + totalRanking); //TODO remove after testing
			}
		}
		if(amountOfRanking != 0) { //at least one occurence in a files
			return (double)totalRanking / amountOfRanking;
		}
		else {
			return -1.0;//no occurence of name in any of the files
		}
	}
	
	public void testGetAverageRank() {
		//String name = "Emma"; String gender = "F";
		//String name = "Mason"; String gender = "M"; // result 3.0
		//String name = "Jacob"; String gender = "M"; //result 2.66
		//Graded Quiz
		//String name = "Susan"; String gender = "F"; // 173.51
		String name = "Robert"; String gender = "M"; // 10.75
		double averageRanking = getAverageRank(name, gender);
		if(averageRanking == -1.0) {
			System.out.println("Name " + name + " is not recorded in any of the files");
		}
		else {
			System.out.println("Average ranking for name " + name + " is " + averageRanking);
		}
	}
	
	/**
	 * method to get the total amount of births before the search name, in the same
	 * year and with the same gender
	 * @param year
	 * @param name
	 * @param gender
	 * @return an integer with the number
	 */
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		CSVParser parser = getParser(year);
		int totPreviousBirths = 0;
		int returnTotalPreviousBirths = 0;
		for(CSVRecord current : parser) {
			if(current.get(1).equalsIgnoreCase(gender)) { //("F")) {
				if(current.get(0).equalsIgnoreCase(name)) { // && current.get(1).equalsIgnoreCase(gender)) {
					returnTotalPreviousBirths = totPreviousBirths;
					return returnTotalPreviousBirths;
				}
				totPreviousBirths += Integer.parseInt(current.get(2));
				//System.out.println("Total births so far: " + totPreviousBirths); //TODO delete after testing
			}
		}
		return returnTotalPreviousBirths = -1;  // the amount of births ranked higher 
	}
	
	public void testGetTotalBirthsRankedHigher() {
		//int year = 2012; String name = "Ethan"; String gender = "M"; //Answer 15
		//int year = 2012; String name = "Ava"; String gender = "F"; //Answer 34
		//int year = 2012; String name = "Sophia"; String gender = "F"; //Answer 0
		//Graded Quiz
		//int year = 1990; String name = "Emily"; String gender = "F"; //Answer 323200
		int year = 1990; String name = "Drew"; String gender = "M"; //Answer 1498074
		int totalBirthBeforeName = this.getTotalBirthsRankedHigher(year, name, gender);
		if(totalBirthBeforeName == -1) {
			System.out.println("The name " + name + " did not appear in " + year);
		}
		else {
			System.out.println("Total amount of births ranked higher than " +
							name + " in " + year + " is: " + totalBirthBeforeName);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BabyNames bn = new BabyNames();
		/*FileResource file = new FileResource();
		bn.totalBirths(file); //*/
		//bn.testTotalNamesByGender();
		//bn.testGetRank();
		//bn.testGetName();
		//bn.testWhatIsNameInYear();
		//bn.testGetRankInFile();
		//bn.testYearOfHighestRank();
		//bn.testGetAverageRank();
		bn.testGetTotalBirthsRankedHigher();
	}

}
