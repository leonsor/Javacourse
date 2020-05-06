/**
 * 
 */
package Week2;

import java.util.*;

import edu.duke.*;

/**
 * @author Leon
 *
 */
public class WordFrequencies {
	
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFrequencies() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	/**
	 * Method to read file, and for every word check whether already in the list.
	 * if not in the list, store as additional string in the list. update 
	 * ArrayList<> myWords
	 */
	public void findUnique() {
		FileResource resource = new FileResource();
		/*ArrayList<String> cleanData = transformWords(resource); //clean words
		for(int i = 0; i < cleanData.size(); i++) {*/ //to be used for words without punctuation
		for(String s : resource.words()) {
			//String s = cleanData.get(i); //to be used for words without punctuation
			s = s.toLowerCase();
			int index = myWords.indexOf(s);
			if(index == -1) { //word is not yet in ArrayList<>
				myWords.add(s);
				int lastIndex = myWords.size() -1 ;//calculate the new index
				myFreqs.add(lastIndex, 1); 
			}
			else {
				int newCount = myFreqs.get(index) + 1;
				myFreqs.set(index, newCount);
			}
		}
	}
	
	public int findIndexOfMax() {
		int highestSoFar = 0;
		int indexLargest = 0;
		for(int i = 0; i < myFreqs.size(); i++) {
			if(myFreqs.get(i) > highestSoFar) {
				highestSoFar = myFreqs.get(i);
				indexLargest = i;
			}
		}
		return indexLargest;
	}
	
	/**
	 * Helper method to transform strings from a word file into clean words,
	 * i.e. no comma's or other characters (besides '-'or '\'')
	 * @param resource
	 * @return ArrayList<String cleanData
	 */
	private ArrayList<String> transformWords(FileResource resource) {
		//ArrayList<String> rawData = new ArrayList<String>();
		ArrayList<String> cleanData = new ArrayList<String>();
//		FileResource resource = new FileResource();
		for(String s : resource.words()) {
			s = s.toLowerCase();
			//System.out.print("raw word: " + s); //TODO deleta after testing
			String sNew = "";
			//for each character c in s
			for(Character c : s.toCharArray()) {
				if(Character.isAlphabetic(c) || c.equals('\'') || c.equals('-')) {// if c = alphanummeric
					sNew = sNew + c;//add to new String
				}
			}
			cleanData.add(sNew); //add String to cleanData
			//System.out.println(" , clean word: " + sNew); //TODO delete after testing
		}
		//System.out.println("Total amount of clean words = " + cleanData.size());
		return cleanData;
	}
	
	/**
	 * Test method for WordFrequencies()
	 */
	public void tester() {
		findUnique();
		System.out.println("# unique words: " + myWords.size());
		/*for(int i = 0; i < myWords.size(); i++) {
			System.out.println("Word: " + myWords.get(i) + " appeared " +
					myFreqs.get(i) + " times");
		}*/
		int index = this.findIndexOfMax();
		System.out.println("The word that occurs most often = " + myWords.get(index) + 
				" ,and it occured " + myFreqs.get(index) + " times");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WordFrequencies wF = new WordFrequencies();
		wF.tester();
		//wF.transformWords();
	}
}
