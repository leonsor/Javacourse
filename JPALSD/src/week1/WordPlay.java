/**
 * Class to transform words from a file into another form
 */
package week1;

/**
 * @author Leon
 *
 */
public class WordPlay {

	/**
	 * Method to test whether a character is a vowel or not
	 * @param ch
	 * @return true or false
	 */
	public boolean isVowel(char ch) {
		//StringBuilder sb = new StringBuilder("a, e, i, o, u, A, E, I, O, U");
		String vowels = ("a, e, i, o, u, A, E, I, O, U");
		if(vowels.indexOf(ch) >= 0 ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Test method for isVowel
	 */
	public void testIsVowel() {
		System.out.println("a " + isVowel('a'));
		System.out.println("O " + isVowel('O'));
		System.out.println("r " + isVowel('r'));
		System.out.println("Z " + isVowel('Z'));
	}
	
	public String replaceVowels(String phrase, char ch) {
		StringBuilder replacedVowels = new StringBuilder(phrase);
		for(int i = 0; i < phrase.length(); i++) {
			if(isVowel(replacedVowels.charAt(i))) {
				replacedVowels.setCharAt(i, ch);
			}
		}
		return replacedVowels.toString();
	}
	
	/**
	 * Test method for replaceVowels
	 */
	public void testReplaceVowels() {
		System.out.println(replaceVowels("Hello World", '*'));
	}
	
	public String emphasize(String phrase, char ch) {
		StringBuilder emphasized = new StringBuilder(phrase);
		char chSecond = ' ';
		if(Character.isUpperCase(ch)) {
			chSecond = Character.toLowerCase(ch);
		}
		else {
			chSecond = Character.toUpperCase(ch);
		}
		char oddChar = '+';
		char evenChar = '*';
		for(int i = 0; i < phrase.length(); i++) {
			if(emphasized.charAt(i) == ch || emphasized.charAt(i) == chSecond) {
				if(i % 2 == 0) {
					emphasized.setCharAt(i, evenChar);
				}
				else {
					emphasized.setCharAt(i, oddChar);
				}
			}
		}
		return emphasized.toString();
	}
	
	public void testEmphasize() {
		System.out.println(emphasize("dna ctgaaactga", 'a'));
		System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WordPlay wordPlay = new WordPlay();
		//wordPlay.testIsVowel();
		//wordPlay.testReplaceVowels();
		wordPlay.testEmphasize();
	}

}
