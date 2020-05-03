/**
 * Class to try out several examples from the videos
 */
package week1;

/**
 * @author Leon
 *
 */
public class TryOut {
	
	private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public String encryptSentence(String original, int key) {
		original = original.toUpperCase();
		String shiftedAlphabet = makeEncryptedAlphabet(key); 
		StringBuilder encrypted = new StringBuilder();
		for(int i = 0 ; i < original.length(); i++) {
			char c = original.charAt(i);
			String search = "" + c;
			int index = alphabet.indexOf(search);
			if(index == -1) {
				encrypted.append(c);
			}
			else {
				encrypted.append(shiftedAlphabet.charAt(index));
			}
		}
		return encrypted.toString();
	}
	
	private String makeEncryptedAlphabet(int i) {
		String begin = alphabet.substring(0, i);
		String end = alphabet.substring(i);
		//System.out.println(begin); System.out.println(end); //TODO test only
		//System.out.println(end+begin); //TODO test only
		return end+begin;
	}

	public void testEncryptSentence() {
		String original = "Programming can be fun, so can cryptography; however they should not be combined";
//		String original = "a bat";
		int key = 17;
		String encrypted = encryptSentence(original, key);
		System.out.println(encrypted);
		String decrypted = encryptSentence(encrypted, 26-key);
		System.out.println(decrypted);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TryOut tO = new TryOut();
		tO.testEncryptSentence();
	}

}
