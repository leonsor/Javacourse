/**
 * Coursera - Java programming: solving problems with software
 * Week 2: Part5 - Lecture code
 */

/**
 * @author Leon
 *
 */
public class Part5 {
	
	public int findStopCodon( String dnaStr,
								int startIndex,
								String stopCodon) {
		int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
		while(currIndex != -1) {
			if((currIndex - startIndex) % 3 == 0) {
				return currIndex;
			}
			else {
				currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
			}
		}
		return dnaStr.length();
	}

	public String findGene(String dna, int where) {
		int startIndex = dna.indexOf("ATG", where);
		if(startIndex == -1) {
			return "" ;
		}
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		int minIndex = 0;
		if(taaIndex == -1 ||
			(tagIndex != -1 && tgaIndex < tagIndex)) {
			minIndex = tgaIndex;
		}
		else {
			minIndex = taaIndex;
		}
		if(minIndex == -1) {
			return "";
		}
		return dna.substring(startIndex, minIndex+3);
	}
	/*
	 * Test method for findStopCodon
	 */
	public void testFindStop() {
		String dna = "xxxyyyxxxTAAxxxyyyzzzTAAxx";
		int dex = findStopCodon(dna, 0, "TAA");
		if(dex != 9) System.out.println("error on 9");
		dex = findStopCodon(dna, 9, "TAA");
		if(dex != 21) System.out.println("error on 21");
		dex = findStopCodon(dna, 21, "TAA");
		if(dex != 26) System.out.println("error on 26");
		dex = findStopCodon(dna, 0, "TAG");
		if(dex != 26) System.out.println("error on 26");
	}
	
	public void printAllGenes(String dna) {
		int startIndex = 0; //Set startindex to 0
		while(true) {
			// Find the next gene after startIndex
			String currentGene = findGene(dna, startIndex);
			//If no gene was found, leave this loop
			if(currentGene.isEmpty()) {
				break;
			}
			System.out.println(currentGene);
			// Print that gene
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
			//Set startIndex to just past the end of the gene
		}
	}
	public void testOn(String dna) {
		System.out.println("Testing printAllgenes" + dna);
		printAllGenes(dna);
	}
	
	public void test() {
		//      ATGv  TAAv  ATG   v  v  TGA 
		testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
		testOn("");
		//      ATGv  v  v  v  TAAv  v  v   ATGTAA
		testOn("ATGATCATAAGAAGATAATAGAGGGGCCATGTAA");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Part5 p5 = new Part5();
//		p5.testFindStop();
		p5.test();
	}
}
