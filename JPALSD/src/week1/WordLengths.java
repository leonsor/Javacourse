/**
 * Breaking the Caesar Cipher
 * Assignment 1
 */
package week1;

import edu.duke.*;

/**
 * @author Leon
 *
 */
public class WordLengths {

	/**
	 * Method to determine the amount of words of a certain lenght
	 * the first and last non-letter characters are not counted.
	 * If they are in the middle of a sentence, they are.
	 * @param resource
	 * @return
	 */
	public int[] countWordLengths(FileResource resource) {
		int[] counts = new int[31];
		for(String s : resource.words()) {
			int length = 0;
			for(int k = 0; k < s.length(); k++) {//(char c : s.toCharArray()) {
				if(Character.isLetter(s.charAt(k))) {
					length += 1;
				}
				else { //not a letter
					if(k!= 0 && k != s.length()-1) { //but is not the first or last character
						length += 1; //need to add one to word length
					}
				}
			}
			counts[length] +=1;
		}
	return counts;
	}
	
	public void testCountWordLengths() {
		FileResource resource = new FileResource();
		int[] counts = this.countWordLengths(resource);
		for(int i = 1; i < counts.length; i ++) {
			System.out.println(counts[i] + " words of length " + i);
		}
		int maxValues = indexOfMax(counts);
		System.out.println("Most common word lenght is " + maxValues);
	}
	
	/**
	 * method to determine which index in int[] values has the highest number
	 * To use in combination with countWordLengths
	 * @param values
	 * @return
	 */
	public int indexOfMax(int[] values) {
		int maxIndex = 0;
		for(int k = 0; k < values.length; k++) {
			if(values[k] > values[maxIndex]) {
				maxIndex = k;
			}
		}
		return maxIndex;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WordLengths wl = new WordLengths();
		wl.testCountWordLengths();
	}

}
