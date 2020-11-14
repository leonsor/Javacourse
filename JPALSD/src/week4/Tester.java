/**
 * 
 */
package week4;

import java.util.HashSet;

import edu.duke.FileResource;

/**
 * @author Leon
 *
 */
public class Tester {

	
	/**
	 * Test for slicing in VignereBreaker
	 */
	public void testSliceString() {
		VigenereBreaker vB = new VigenereBreaker();
		String message = "abcdefghijklm"; //message to slice
		int whichSlice = 0; //index the slice should start from
		int totalSlices = 4;//indicating the length of the key
		String s = vB.sliceString(message, whichSlice, totalSlices);
		System.out.println(s); //print the sliced String
	}
	
	/**
	 * Tester mentod for tryKeyLength in VignereBreaker
	 */
	public void testTryKeyLength() {
		VigenereBreaker vB = new VigenereBreaker();
		FileResource fr = new FileResource();
		String message = fr.asString();
		int klength = 38;
		char mostCommon = 'e';
		int[] key = vB.tryKeyLength(message, klength, mostCommon);
		for(int i = 0; i < key.length ; i++) {
			System.out.println("key found: " + key[i]);
		}
	}
	
	public void testReadDictionary() {
		VigenereBreaker vB = new VigenereBreaker();
		FileResource fr = new FileResource();
		HashSet<String> dictionary = vB.readDictionary(fr);
		System.out.println("Amount of words in dictionary: " + dictionary.size());
		/*for(String word : dictionary) {// possibility to print out all words
			System.out.println(word);
		}*/
	}
	
	public void testCountWords () {
		VigenereBreaker vB = new VigenereBreaker();
		String message = vB.breakVigenere();
		FileResource fr = new FileResource();
		HashSet<String> dictionary = vB.readDictionary(fr);
		int n = vB.countWords(message, dictionary);
		System.out.println("Total amount of real words: " + n);
	}
	
	public void testMostCommonCharin() {
		FileResource fr = new FileResource(); //read in dictionary file
		VigenereBreaker vB = new VigenereBreaker();
		HashSet<String> dictionary = vB.readDictionary(fr);
		char c = vB.mostCommonCharin(dictionary);
		System.out.println("Most common character in Dictionary " + c);
	}
	
	
	
	/**
	 * Test method for breakVigenere ()
	 */
	public void testBreakVignere() {
		VigenereBreaker vB = new VigenereBreaker();
		String message = vB.breakVigenere();
		System.out.println("---testBreakVignere----- Start of decrypted message ------");
		String messageStart = message.substring(0,80);
		System.out.println(messageStart);
	}
	
	public void testBreakForLanguage() {
		VigenereBreaker vB = new VigenereBreaker();
		String message = vB.breakVigenere();
		FileResource fr = new FileResource();
		HashSet<String> dictionary = vB.readDictionary(fr);
		String decrypted = vB.breakForLanguage(message, dictionary);
		String decryptedStart = decrypted.substring(0, 80);
		System.out.println(decryptedStart);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tester te = new Tester();
		VigenereBreaker vB = new VigenereBreaker();
		vB.readAllDictionaries();
		//te.testSliceString(); //test separately
		//te.testTryKeyLength(); //test separately
		//te.testBreakVignere(); //last test for practical quiz week 4
		//te.testReadDictionary(); //Test for read dictionary
		//te.testCountWords(); //Test for countWords. First select encrypted file, than select dictionary
		//te.testBreakForLanguage();//check with 100 keylengths
		//te.testBreakVignere(); //test after implementing last changes programming exercise
		//te.testMostCommonCharin();
	}

}