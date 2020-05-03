/**
 * Assignment 1 - One Key OO principles
 */
package week1;

/**
 * Class to encrypt and decrypt Strings using a fixed key
 * @author Leon
 *
 */
public class CaesarCypher {
	
	private static final String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//UpperCase alphabet
	private static final String alphabetLower = "abcdefghijklmnopqrstuvwxyz";//LowerCase alphabet
	private String shiftedAlphabetUpper;
	private String shiftedAlphabetLower;
	private int mainKey;
	
	/**
	 * Constructor to create an instance of CaesarCypher with a fixed key
	 */
	public CaesarCypher(int key) {
		shiftedAlphabetUpper = makeEncryptedAlphabet(alphabetUpper, key); 
		shiftedAlphabetLower = makeEncryptedAlphabet(alphabetLower, key);
		mainKey = key;
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
	 * Method to encrypt a string using an encrypted alphabet
	 * works with upper and lower strings
	 * @param original
	 * @return the encrypted string
	 */
	public String encrypt(String original) {
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
	
	
	public String decrypt(String input) {
		CaesarCypher ccDecrypt = new CaesarCypher(26-mainKey);
		return ccDecrypt.encrypt(input);
	}
	
	public void test() { //internal tests only
		/*String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");		
		System.out.println(encrypted);
		System.out.println(decrypt(encrypted));*/
		//For Final assessment week 1
		String test = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		System.out.println("encrypted: " + this.encrypt(test));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CaesarCypher cC = new CaesarCypher(15);
		cC.test();
	}

}
