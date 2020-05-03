
public class Part2 {
	
	public int howMany(String stringa, String stringb) {
		int times = 0;
		int nextIndex = 0;
		int currIndex = stringb.indexOf(stringa, nextIndex);
		while(currIndex != -1 && !stringa.isEmpty()) {
			times += 1;
			nextIndex = currIndex + stringa.length();
			currIndex = stringb.indexOf(stringa, nextIndex);
		}
		return times; //TODO change into real return variable
	}
	
	public void testHowMany() {
/*		
		String stringa = "AA"; // occurence is 2
		String stringb = "ATAAAA"; */
/*		String stringa = "GAA"; // occurence is 3
		String stringb = "ATGAACGAATTGAATC"; */
/*		String stringa = "GAA"; // occurence is 0
		String stringb = "ATGACCGATTTGAGATC"; */
/*		String stringa = ""; // occurence is 0
		String stringb = "ATGACCGATTTGAGATC"; */
		String stringb = ""; // occurence is 0
		String stringa = "ATGACCGATTTGAGATC";
		System.out.println("Amount of occurences of " + stringa + " = " + howMany(stringa, stringb));
	}
	
	public void testQuiz() {
		String dna = "CTGCCTGCATGATCGTA";
		int pos = dna.indexOf("TG");
		int count = 0;
		while (pos >= 0) {
		  count = count + 1;
		  pos = dna.indexOf("TG",pos);
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		Part2 p2 = new Part2();
		//p2.testHowMany();
		p2.testQuiz();
	}

}
