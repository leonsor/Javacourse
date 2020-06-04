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
	 * The method should  read each line in fr, convert to lower case and 
	 * than return the HashSet representing the words in a dictionary
	 * @param fr
	 * @return HashSet<String> which contains all words in the dictionary file
	 */
	public HashSet<String> readDictionary(FileResource fr) {
		HashSet<String> dictionary = new HashSet<String>();
		for(String word : fr.words()) {
			word.toLowerCase();
			dictionary.add(word);
		}
		return dictionary;
	}
	
	/**
	 * This method should split the message into words,  iterate over those words, 
	 * and see how many of them are “real words”.  return the integer count
	 * @param message
	 * @param dictionary
	 * @return how many valid words were found
	 */
	public int countWords(String message, HashSet<String> dictionary) {
		int count = 0;
		String[] wordList= message.split("\\W+");
		for(String word : wordList) {
			if(dictionary.contains(word.toLowerCase())) {
				count++;
			}
		}
		return count;
	}
	
	public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
		int maxCount = 0;
		HashMap<Integer,int[]> counting = new HashMap<Integer, int[]>();
		for(int i = 1; i < 10; i++) {
			int[] key = this.tryKeyLength(encrypted, i, 'e');//find key for each key length
			VigenereCipher vC = new VigenereCipher(key);
			String decrypted = vC.decrypt(encrypted);//decrypt message
			int k = this.countWords(decrypted, dictionary);//count words in english
			System.out.println(k + " words found for key lenght " + i);//TODO delete after testing
			counting.put(i, key);//store i and count in HashMap
			if(k > maxCount) {
				maxCount = k;
			}
		}
		VigenereCipher vC = new VigenereCipher(counting.get(maxCount));
		return vC.decrypt(encrypted);
	}

    /**
     * Method to break a message encrypted with a Vigenere Cypher
     * Known language, known key lenght
     */
	public String breakVigenere () {
       FileResource fr = new FileResource();
       String message = fr.asString();
       int klength = 5;
       char mostCommon = 'e';
       int[] key = this.tryKeyLength(message, klength, mostCommon);
       VigenereCipher vC = new VigenereCipher(key);
       String decrypted = vC.decrypt(message);
       System.out.println(decrypted);
       return decrypted;
    }
}
