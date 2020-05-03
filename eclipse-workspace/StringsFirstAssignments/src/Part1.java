/**
 * Coursera - Java programming: solving problems with software
 * Week 2: Part1 
 */

/**
 * @author Leon
 *
 */

public class Part1 {

	public String findSimpleGene(String dna) {
		// Find index of startcode ATG
		int startIndex = dna.indexOf("ATG");
		if(startIndex != -1) {// if no ATG, return no DNA
			int endIndex = dna.indexOf("TAA");// Find index of stopcode TAA
			if(endIndex != -1) {//in no TAA, return no DNA
				if ((endIndex - startIndex) % 3 == 0) {// check stopcode - startcode is multiple of 3
					//if true, create substring s (startcode, stopcode + 3)
					String s = dna.substring(startIndex, endIndex+3);
					return s;//return DNA
				}
			}
		}
		return("no DNA found");//return no DNA
	}// EO Method
	
	public void testSimpleGene() {
//		String dna = "ATGGGTTAAGTC"; //correct
//		String dna = "ATAGGTTAAGTC"; //no ATG
//		String dna = "ATGGGTTAGGTC"; //no TAA 
//		String dna = "ATGGGTCTTAAGTC"; //not mod 3
		String dna = "AAATGCCCTAACTAGATTAAGAAACC"; //Quiz question 1
		System.out.println(dna);
		System.out.println(findSimpleGene(dna));		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Part1 p = new Part1();
		p.testSimpleGene();
	} //EOM
} //EOC
