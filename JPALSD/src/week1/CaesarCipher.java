/**
 * Assignment 2
 */
package week1;

/**
 * @author Leon
 *
 */
public class CaesarCipher {

	private static String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//UpperCase alphabet
	private static String alphabetLower = "abcdefghijklmnopqrstuvwxyz";//LowerCase alphabet
	/**
	 * Method to encrypt a string using an encrypted alphabet with a given key
	 * works with upper and lower strings
	 * @param original
	 * @param key
	 * @return the encrypted string
	 */
	public String encrypt(String original, int key) {
		String shiftedAlphabetUpper = makeEncryptedAlphabet(alphabetUpper, key); 
		String shiftedAlphabetLower = makeEncryptedAlphabet(alphabetLower, key);
		StringBuilder encrypted = new StringBuilder(original);
		for(int i = 0 ; i < original.length(); i++) {
			char c = original.charAt(i);
			String search = "" + c;
			if(Character.isLowerCase(c)) {
				int index = alphabetLower.indexOf(search);
				if(index != -1) {
					encrypted.setCharAt(i, shiftedAlphabetLower.charAt(index));
				}
			}
			else {
				int index = alphabetUpper.indexOf(search);
				if(index != -1) {
					encrypted.setCharAt(i, shiftedAlphabetUpper.charAt(index));
				}
			}
		}
		return encrypted.toString();
	}
	
	/**
	 * Helper method to create an encrypted alphabet
	 * @param alphabet
	 * @param i - the shift 
	 * @return the new alphabet to use 
	 */
	private String makeEncryptedAlphabet(String alphabet, int i) {
		String begin = alphabet.substring(0, i);
		String end = alphabet.substring(i);
		return end+begin;
	}
	
	/**
	 * Test method for the Caesar Cipher
	 */
	public void testCaesarCipher() {
		//System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
		//System.out.println(encrypt("First Legion", 17));
		String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);		
		System.out.println(encrypted);
		System.out.println(encrypt(encrypted, 26-15));
	}
	
	/**
	 * Method to encrypt the original String with 2 keys, even characters with key1,
	 * odd characters with key2
	 * @param original
	 * @param key1
	 * @param key2
	 * @return The encrypted String
	 */
	public String encryptTwoKeys(String original, int key1, int key2) {
		String shiftedAlphabetUpperKey1 = makeEncryptedAlphabet(alphabetUpper, key1); 
		String shiftedAlphabetLowerKey1 = makeEncryptedAlphabet(alphabetLower, key1);
		String shiftedAlphabetUpperKey2 = makeEncryptedAlphabet(alphabetUpper, key2); 
		String shiftedAlphabetLowerKey2 = makeEncryptedAlphabet(alphabetLower, key2);
		StringBuilder encrypted = new StringBuilder(original);
		for(int i = 0 ; i < original.length(); i++) {
			char c = original.charAt(i);
			String search = "" + c;
			if(Character.isLowerCase(c)) {
				int index = alphabetLower.indexOf(search);
				if(index != -1) {
					if(i % 2 == 0) {// even characters in String - use key 1
						encrypted.setCharAt(i, shiftedAlphabetLowerKey1.charAt(index));
					}
					else {
						encrypted.setCharAt(i, shiftedAlphabetLowerKey2.charAt(index));
					}
				}
			}
			else {
				int index = alphabetUpper.indexOf(search);
				if(index != -1) {
					if(i % 2 == 0) {
						encrypted.setCharAt(i, shiftedAlphabetUpperKey1.charAt(index));
					}
					else {
						encrypted.setCharAt(i, shiftedAlphabetUpperKey2.charAt(index));
					}
				}
			}
		}
		return encrypted.toString();
	}
	
	public void testEncryptTwoKeys() {
		//System.out.println(this.encryptTwoKeys("First Legion", 23, 17));
		System.out.println(this.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
		System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24,6)); //Practice Quiz, Q 8. Answer: Run like wild to beat the wind
		System.out.println(this.encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CaesarCipher cC = new CaesarCipher();
		//cC.testCaesarCipher();
		cC.testEncryptTwoKeys();

	}

}
