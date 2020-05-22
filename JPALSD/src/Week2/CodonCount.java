/**
 * 
 */
package Week2;

import java.util.*;

import edu.duke.FileResource;

/**
 * @author Leon
 *
 */
public class CodonCount {
	
	HashMap<String, Integer> dnaCount;

	public CodonCount() {
		dnaCount = new HashMap<String, Integer>();
	}
	
	/**
	 * This method will build a new map of codons mapped to their counts 
	 * from the string dna with the reading frame with the position start 
	 * (a value of 0, 1, or 2).
	 * @param start
	 * @param dna
	 */
	private void buildCodonMap(int start, String dna) {
		dnaCount.clear();
		while(start+3 <= dna.length()) {
			String codon = dna.substring(start, start+3);
			//System.out.println(codon); //TODO delete after testing
			if(codon.length() == 3 && Character.isLetter(codon.charAt(2))) {
				if(dnaCount.containsKey(codon)) {
					dnaCount.put(codon, dnaCount.get(codon)+1);
				}
				else {
				dnaCount.put(codon, 1);
				}
			}
			start +=3;
		}
	}
	
	/**
	 * This method returns a String, the codon in a reading frame that has the largest count
	 * @return
	 */
	private String getMostCommonCodon() {
		String largestCodon = "";
		if(!dnaCount.isEmpty()) {
			int size = 0;
			for(String dna : dnaCount.keySet()) {
				int occurences = dnaCount.get(dna);
				if(occurences > size) {
					largestCodon = dna;
					size = occurences;
				}
			}
			return largestCodon;
		}
		return "No Codon found";
	}
	
	/**
	 * Method to print all codons in the HashMap and their counts 
	 * if their count is between start and end
	 * @param start
	 * @param end
	 */
	private void printCodonCounts(int start, int end) {
		System.out.println("Counts of codons between " + start + "and " + end + " inclusive are: ");
		for(String dna : dnaCount.keySet()) {
			int occurence = dnaCount.get(dna);
			if(occurence >= start && occurence <= end) {
				System.out.println(dna + "\t" + "occured " + occurence + " times");
			}
		}
	}
	
	private void tester() {
		FileResource file = new FileResource();
		String dnaStrand = file.asString();
		dnaStrand.toUpperCase();
		System.out.println(dnaStrand);//TODO delete after testing
		for(int i = 0; i < 3; i++) {
			this.buildCodonMap(i, dnaStrand);
			System.out.println("Total unique codons for reading frame " + i + ": " + dnaCount.size());
			String commonCodon = this.getMostCommonCodon();
			System.out.println("and most common codon = " + commonCodon + " with count " + 
					dnaCount.get(commonCodon));
			printCodonCounts(1,7);
		}	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CodonCount cC = new CodonCount();
		/*String dna = "CGTTCAAGTTCAA";
		cC.buildCodonMap(0, dna);
		String s = cC.getMostCommonCodon();
		int amount = cC.dnaCount.get(s);
		System.out.println("most common codon is " 
		+ s +" with count " + amount);
		cC.printCodonCounts(0, 2);*/
		cC.tester();
	}
}
