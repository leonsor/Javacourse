/**
 * Coursera - Java programming: solving problems with software
 * Week 2: Part2 
 */

/**
 * @author Leon
 *
 */
public class Part2 {
	
	public String findSimpleGene(String dna, String startCodon, String stopCodon) {
		// find out if dna string is upper or lower case
		if(Character.isLowerCase(dna.charAt(0))) {
			String tempStart = startCodon.toLowerCase(); // make startCodon lower case
			startCodon = tempStart;
			String tempStop = stopCodon.toLowerCase(); // make stopCodon lower case
			stopCodon = tempStop;
			System.out.println("dna String was lower case");
		}
		else {
			System.out.println("dna String was UPPER CASE");// test purposes only
		}
		int startIndex = dna.indexOf(startCodon);// Find index of startcode ATG
		if(startIndex != -1) {// if no startCodon, return no DNA
			int endIndex = dna.indexOf(stopCodon);// Find index of stopcode TAA
			if(endIndex != -1) {//if no stopCodon, return no DNA
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
		String startCodon = "ATG";
		String stopCodon = "TAA";
		String dna = "ATGGGTTAAGTC"; //correct
//		String dna = "atgggttaagtc"; // correct - lower case
//		String dna = "ATAGGTTAAGTC"; //no ATG
//		String dna = "ATGGGTTAGGTC"; //no TAA 
//		String dna = "ATGGGTCTTAAGTC"; //not mod 3
//		String dna = "ataggttaagtc"; //no ATG – Lower case
//		String dna = "atgggttaggtc"; //no TAA – Lower case
//		String dna = "atgggtcttaagtc"; //not mod 3 – lower case

		System.out.println(dna);
		System.out.println(findSimpleGene(dna, startCodon, stopCodon));		
	} // EO Method
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Part2 p2 = new Part2();
		p2.testSimpleGene();
	} // EOM

} //EO Class
