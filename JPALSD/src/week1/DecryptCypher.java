/**
 * Class to test the video example on decrypting the Ceasar Cypher
 */
package week1;

import edu.duke.FileResource;

/**
 * @author Leon
 *
 */
public class DecryptCypher {
	
	public static String alph = "abcdefghijklmnopqrstuvwxyz";
//	public static String encrypted = "Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!"; 
	public static String original = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
	
	/**
	 * Method to count letters in a string which exist in the alph String
	 * @param message
	 * @return a Array<int> with the amount of times a letter occurs in String message
	 */
	 public int[] countLetters(String message) {
		int[] counts = new int[26];
		for(int k=0; k<message.length(); k++) {
			char c = Character.toLowerCase(message.charAt(k));
			int dex = alph.indexOf(c);
			if(dex != -1 ) {
				counts[dex] +=1;
			}
		}
		return counts;
	}
	
	 /**
	  * Method to decrypt an encrypted string using the method to find the most frequent
	  * letter in the alphabet (e - index 4) and calculate backwards
	  * @param encrypted
	  * @return the decrypted String
	  */
	 public String decrypt(String encrypted) {
		 CaesarCipher cc = new CaesarCipher();
		 int[] freqs = countLetters(encrypted);
		 int maxDex = maxIndex(freqs);
		 int dkey = maxDex - 4;
		 if(maxDex < 4) {
			 dkey = 26 - (4 - maxDex);
		 }
		 System.out.println("Key used for decryption: " + (dkey));
		 return cc.encrypt(encrypted, 26-dkey);
	 }
	 
	 /**
	  * Helper method to find the highest number in an ArrayList<int>
	  * @param vals
	  * @return
	  */
	 private int maxIndex(int[] vals) {
		int maxDex = 0;
		for(int k = 0; k < vals.length; k++) {
			if(vals[k] > vals[maxDex]) {
				maxDex = k;
			}
		}
		return maxDex;
	}
	 
	 public void testDecrypt() {
		 CaesarCipher cc = new CaesarCipher();
		 String encrypted = cc.encrypt(original, 15);
		 System.out.println(encrypted);
		 String decrypted = decrypt(encrypted);
		 System.out.println(decrypted);
		 
	 }
	 
	 public void testCountLetters() {
		 int[] counts = this.countLetters(original);
		 for(int k = 0; k < counts.length; k++) {
			 System.out.println(alph.charAt(k) + "\t" + "amount: " + counts[k]);
		 }
		 System.out.println("Letter with maximum amount of occurences: " + alph.charAt(maxIndex(counts)));
	 }
	 
	 public void testMaxIndex() {
	 }

	 /**
	  * Method to decrypt an encrypted string with two keys
	  * Split the encrypted string in two sub-strings, find the key for each sub-string 
	  * and decrypt each sub string individually. Afterwards, re-compose the full string
	  * @param encrypted string
	  * @return decrypted string
	  */
	 public String decryptTwoKeys(String encrypted) {
		StringBuilder sbEncryptedEven = new StringBuilder();
		StringBuilder sbEncryptedOdd = new StringBuilder();
		for(int k = 0; k < encrypted.length(); k++) { //split encrypted string in two
			if(k % 2 == 0) {
				sbEncryptedEven.append(encrypted.charAt(k));//even chars (0, 2, 4, etc)
			}
			else {
				sbEncryptedOdd.append(encrypted.charAt(k)); //odd chars (1, 3, 5 etc)
			}
		}
		String decryptedEven = this.decrypt(sbEncryptedEven.toString());		
		String decryptedOdd = this.decrypt(sbEncryptedOdd.toString());
		StringBuilder decrypted = new StringBuilder();
		for(int k = 0; k < decryptedEven.length(); k++) { //re-construct string
			decrypted.append(decryptedEven.charAt(k)); //add first char
			if(k < decryptedOdd.length()) { //check for existing last character
				decrypted.append(decryptedOdd.charAt(k)); //add second char
			}
		}
		return decrypted.toString();
	 }
	 
	 
	 /**
	  * Test method to find and print the two encryption keys and the decrypted string
	  */
	 public void testDecryptTwoKeys() {
		 //CaesarCipher cc = new CaesarCipher();
		 //String original = "Just a test string with lots of eeeeeeeeeeeeeeeees";
		 String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko"; //for Practice quiz Q9
		 //String encrypted = cc.encryptTwoKeys(original, 23, 2);
		 //System.out.println(original);
		 //System.out.println(encrypted);
		 String decrypted = this.decryptTwoKeys(encrypted);
		 System.out.println(decrypted);
	 }
	 
	 public void decryptFile() { // for Practice quiz Q10
		 FileResource fr = new FileResource();
		 String sb = fr.asString();
		 String decrypted = this.decryptTwoKeys(sb);
		 System.out.println(decrypted); // answer: Geometric computing research at Duke. Keys 17,4
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecryptCypher DC = new DecryptCypher();
		//DC.testDecrypt();
		//DC.testCountLetters();
		DC.testDecryptTwoKeys();
		//DC.decryptFile();
	}

}
