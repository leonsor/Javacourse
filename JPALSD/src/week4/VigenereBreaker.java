package week4;

import java.nio.file.Path;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
	
	private HashMap<String, HashSet<String>> dictionaries;
	private ArrayList<String> languages;
	
	public VigenereBreaker() {
		dictionaries = new HashMap<String, HashSet<String>>();
		languages = new ArrayList<String>();
		languages.add("Danish"); languages.add("Dutch"); languages.add("English"); languages.add("French");
		languages.add("German"); languages.add("Italian"); languages.add("Portuguese"); languages.add("Spanish");
		System.out.println("Total amount of languages added: " + languages.size());
		//System.out.println("Current directory is" + System.getProperty("user.dir")); TODO delete after testing
	}
    
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
     * This method should make use of the CaesarCracker class, as well as the sliceString method, to find the shift for each 
     * index in the key. You should fill in the key (which is an array of integers) and return it.
     * @param encrypted represents the encrypted message
     * @param klength represents the key length
     * @param mostCommon indicates the most common character in the language of the message
     * @return the key used for encrypting the message
     */
	public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i < klength ; i++) {
        		CaesarCracker cC = new CaesarCracker(mostCommon);
        		String partEncrypted = this.sliceString(encrypted, i, klength);
        		int keyFound = cC.getKey(partEncrypted);
        		//System.out.println("key number " + i + " found: " + keyFound); //TODO delete after printing
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
		for(String word : fr.lines()) {
			word = word.toLowerCase();
			dictionary.add(word);
		}
		return dictionary;
	}
	
	public void readAllDictionaries () {
		for(String language : languages) {
			String currDirectory = System.getProperty("user.dir");
			String path = "\\data\\week4\\dictionaries\\";
			String fileName = language;
			String dictionaryName = currDirectory + path + fileName;
			//System.out.println(dictionaryName);//TODO delete after testing
			FileResource fr = new FileResource(dictionaryName);
			HashSet<String> dictionary = this.readDictionary(fr);
			System.out.println("Finished reading dictionary " + language);
			System.out.println(dictionary.size() + " words read");
			dictionaries.put(language, dictionary);
		}
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
		//message = message.toLowerCase();//added after mistake in wordcount
		//String[] wordList= message.split("\\W+");
		//for(String word : wordList) {
		for(String word : message.split("\\W+")) {
			if(dictionary.contains(word.toLowerCase())) {
				count++;
			}
		}
		return count;
	}
	
	public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
		int maxCount = 0;
		HashMap<Integer,int[]> counting = new HashMap<Integer, int[]>();
		for(int i = 1; i < 100; i++) {
			int[] key = this.tryKeyLength(encrypted, i, 'e');//find key for each key length
			VigenereCipher vC = new VigenereCipher(key);
			String decrypted = vC.decrypt(encrypted);//decrypt message
			int k = this.countWords(decrypted, dictionary);//count words in english
			System.out.print(k + " words found for key lenght " + i);//TODO delete after testing
			System.out.println("----BreakForLanguage, fist part " + decrypted.substring(0,20)+ "------------");//TODO delete after testing
			counting.put(k, key);//store i and count in HashMap
			if(k > maxCount) {
				System.out.println("\t Key found: " + Arrays.toString(key));//TODO delete after testing
				maxCount = k;
			}
		}
		System.out.println("breakForLanguage - maxCount = " + maxCount);
		int[] key = counting.get(maxCount);
		System.out.println("breakForLanguage - int[] key = " + Arrays.toString(key));
		VigenereCipher vC = new VigenereCipher(key);
		return vC.decrypt(encrypted);
	}
	
	/**
	 * This method should find out which character, of the letters in the English alphabet, 
	 * appears most often in the words in dictionary. It should return this most commonly 
	 * occurring character
	 * @param dictionary
	 * @return most common character
	 */
	public char mostCommonCharin(HashSet<String> dictionary) {
		char mostCommon = 'e';
		HashMap<Character, Integer> characters = new HashMap<Character, Integer>();
		System.out.println("Start reading dictionary word for word");
		for(String s : dictionary) {
			for(int i = 0 ; i < s.length(); i++ ) {
				char c = s.charAt(i);
				if(characters.containsKey(c)) {
					characters.put(c, characters.get(c)+1);
				}
				else {
					characters.put(c, 1);
				}
			}
		}
		System.out.println("Fihnished reading dictionary, total different characters " +
				characters.size());
		int max = 0;
		for(char c : characters.keySet()) {
			int occurence = characters.get(c);
			if(occurence > max) {
				max = occurence;
				mostCommon = c;
				System.out.println(c + "  Occurs " + occurence + " times");
			}
		}
		return mostCommon;
	}
	
	public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
		
	}

    /**
     * Method to break a message encrypted with a Vigenere Cypher
     * Known language, known key lenght
     */
	public String breakVigenere () {
       FileResource fr = new FileResource();
       String encrypted = fr.asString();
       FileResource frDict = new FileResource();
       HashSet<String> dictionary = readDictionary(frDict);
       char mostCommon = 'e';
       String decrypted = breakForLanguage(encrypted, dictionary);
       return decrypted;
    }
}