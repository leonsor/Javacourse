/**
 * Test class for Programming Exercise week 1
 */
package week1;

import edu.duke.FileResource;

/**
 * @author Leon
 *
 */
public class TestCaesarCypher {
	public static String alph = "abcdefghijklmnopqrstuvwxyz";
	/**
	 * 
	 */
	public TestCaesarCypher() {
		// TODO Auto-generated constructor stub
	}

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
	 
	 /**
	  * Method to break the CeasarCypher code
	  * @param input
	  */
	 public String breakCaesarCypher(String input) {
		 int[] freqs = countLetters(input);
		 int maxDex = maxIndex(freqs);
		 int dkey = maxDex - 4;
		 if(maxDex < 4) {
			 dkey = 26 - (4 - maxDex);
		 }
		 System.out.println("Key used for decryption: " + (dkey));
		 CaesarCypher cc = new CaesarCypher(26-dkey);
		 return cc.encrypt(input);
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
	 
	 
	 public void simpleTest() {
		 FileResource resource = new FileResource();//read a file as String
		 String input = resource.asString();
		 CaesarCypher cC = new CaesarCypher(18);//Create a CaesarCypher object key 18
		 String encrypted = cC.encrypt(input);// encrypt the String
		 System.out.println(encrypted);//print encrypted string
		 String decrypted = cC.decrypt(encrypted); //decrypt the encrypted string
		 System.out.println("The decrypted original string: " + decrypted);//print the original string
		 String returnFromBreakCeasarCypher = this.breakCaesarCypher(encrypted);
		 System.out.println("The return from break-method: " + returnFromBreakCeasarCypher);
	 }
	 
	 /*public void testForFinal() {
		 CaesarCypher cc = new CaesarCypher(15);
		 System.out.println()
	 }*/ //not used!
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCaesarCypher tCC = new TestCaesarCypher();
		tCC.simpleTest();
	}

}
