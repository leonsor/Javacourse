/**
 * Assignment 2: Words in Files
 */
package Week2;

import java.io.*;
import java.util.*;

import edu.duke.*;

/**
 * @author Leon
 *
 */
public class WordsinFiles {
	private HashMap<String, ArrayList<String>> wordMap;
	
	public WordsinFiles() {
		wordMap = new HashMap<String, ArrayList<String>>();
	}

	/**
	 * This method should add all the words from f into the map. If a word is not in the map,
	 *  then you must create a new ArrayList of type String with this word, and have the word
	 *  map to this ArrayList. If a word is already in the map, then add the current filename
	 *  to its ArrayList, unless the filename is already in the ArrayList
	 * @param f
	 */
	private void addWordsFromFile(File f) {
		String fileName = f.getName();
		FileResource fr = new FileResource(f);
		ArrayList<String> newWord = new ArrayList<String>();
		for(String word : fr.words()) {
			System.out.println(word); //TODO delete after testing
			if(wordMap.containsKey(word)) {
				ArrayList<String> currentArray = wordMap.get(word);
				currentArray.add(fileName);
				newWord = currentArray;
				wordMap.put(word, currentArray);
			} 
			else {
				newWord = new ArrayList<String>();
				newWord.add(fileName);
				wordMap.put(word, newWord);
			}
			System.out.println(wordMap + "\n"); //TODO delete after testing
		}
	}
	
	/**
	 * This method first clears the map, and then uses a DirectoryResource 
	 * to select a group of files. For each file, it puts all of its words into the map 
	 * by calling the method addWordsFromFile
	 */
	private void buildWordFileMap() {
		wordMap.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			this.addWordsFromFile(f);
		}
	}
	
	private int maxNumber() {
		int maxNumber = 0;
		for(String A : wordMap.keySet()) {
			int size = wordMap.get(A).size();
			if(size > maxNumber) {
				maxNumber = size; 
			}
		}
		return maxNumber;
	}
	
	public void test() {
		this.buildWordFileMap();
		System.out.println("words in wordMap: " + wordMap.size());
		for(String A : wordMap.keySet()) {
			ArrayList<String> temp = wordMap.get(A);
			System.out.println("Woord " + A + " lenght = " + temp.size());
			for(int i = 0; i < temp.size(); i++) {
				System.out.println(temp.get(i));
			}
		}
		System.out.println("Max amount of file occurences: " + this.maxNumber());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WordsinFiles wf = new WordsinFiles();
		wf.test();
	}

}
