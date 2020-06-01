package week4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    
	/**
	 * This method returns a String consisting of every totalSlices-th character from message, starting at the whichSlice-th 
	 * character
	 * @param message
	 * @param whichSlice
	 * @param totalSlices
	 * @return Sliced-String
	 */
	public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder returnMessage = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i = i + totalSlices) {
        		returnMessage = returnMessage.append(message.charAt(i));
        }
        return returnMessage.toString();
    }

    /**
     * 
     * @param encrypted represents the encrypted message
     * @param klength represents the key length
     * @param mostCommon indicates the most common character in the language of the message
     * @return This method should make use of the CaesarCracker class, as well as the sliceString method, to find the shift for each 
     * index in the key. You should fill in the key (which is an array of integers) and return it.
     */
	public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i < klength ; i++) {
        		CaesarCracker cC = new CaesarCracker(mostCommon);
        		String partEncrypted = this.sliceString(encrypted, i, klength);
        		int keyFound = cC.getKey(partEncrypted);
        		System.out.println("key nuber " + i + " found: " + keyFound); //TODO delete after printing
        		key[i] = keyFound;
        }
        return key;
    }

    /**
     * Method to break a message encrypted with a Vigenere Cypher
     * Known language, known key lenght
     */
	public void breakVigenere () {
       FileResource fr = new FileResource();
       String message = fr.asString();
       int klength = 4;
       char mostCommon = 'e';
       int[] key = this.tryKeyLength(message, klength, mostCommon);
       VigenereCipher vC = new VigenereCipher(key);
       String decrypted = vC.decrypt(message);
       System.out.println(decrypted);
    }
}
