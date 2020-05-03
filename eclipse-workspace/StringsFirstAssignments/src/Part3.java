/**
 * Coursera - Java programming: solving problems with software
 * Week 2: Part3 
 */

/**
 * @author Leon
 *
 */
public class Part3 {

	public boolean twoOccurences( String stringa, String stringb) {
		int counter = 0; // declare counter
		int startIndex = 0; // declare startIndex
		int lenghtA = stringa.length();// declare lenght of stringa
		int lengthB = stringb.length();// declare length of stringb
		while(startIndex < lengthB ) { // until end of stringb
			int foundIndex = stringb.indexOf(stringa, startIndex);// note foundIndex of occurence stringa in stringb
			if(foundIndex != -1) {// if found
				System.out.println("gevonden !"); // Test Only
				counter += 1;// add 1 to counter
				startIndex = foundIndex + lenghtA; //set startIndex to foundIndex + stringa.length
			}
			else {
				break;
			}
		}//EO while loop
		if(counter >= 2) {
			System.out.println("Counter = " + counter); //Test Only
			return true;
		}
		else {
			return false;
		}
	} //EO Method
	
	public String lastPart(String stringa, String stringb) {
		String returnString = stringb;
//		int startIndex = 0; 
		int lengthA = stringa.length();// declare lenght of stringa
		int foundIndex = stringb.indexOf(stringa);
		if(foundIndex != -1) {
			int startIndex = foundIndex + lengthA;// declare new startIndex for substring
			returnString = stringb.substring(startIndex);
		}
		return returnString;
	}
	
	public void testing() { 
//Test for twoOccurences
		String string1 = "banana";//test 1
		String testString1 = "a";// test 1 pass
//		String testString2 = "c";// test2 fail
//		String string3 = "A story by Abby Long";// test 2
//		String testString3 = "by";// test 2
		boolean result = twoOccurences(testString1, string1);
		System.out.println(result);
		
//Test for lastPart
		String string4 = "banana";
		String test4a = "ana";//pass-result na
		String test4b = "c";//fail - result banana
		String string5 = "forest";
		String test5a = "zoo"; //fail - result forest
		String test5b = "st"; //pass - result ""
//		String result = lastPart(test5a, string5);
//		System.out.println("String = " + result);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Part3 p3 = new Part3(); //make new occurence Part3
		p3.testing(); // run testing method
	}

}
