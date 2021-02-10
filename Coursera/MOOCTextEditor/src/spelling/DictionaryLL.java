package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{
	private LinkedList<String> dict;
    // TODO: Add a constructor - Done
	public DictionaryLL () {
		dict = new LinkedList<String>();
		//System.out.println("dict created, size is " + dict.size()); //TODO delete after testing
	}

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	if(word.isEmpty()) { //if word is empty string
    		return false;
    	}
    	String wordLower = word.toLowerCase();//change to lowercase word
//    	System.out.println("Word is: " + word); //TODO delete after testing
    	if(!dict.contains(wordLower)) { //word does not exist yet
   			dict.add(wordLower); //add word to dict
//   			System.out.println("word " + word + " is added. Size is: " + dict.size()); //TODO delete after testing
   			return true;
    	} else { //word already exists
//    		System.out.println("word " + word + " already exists"); //TODO delete after testing
    		return false;
    	}
    }

    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method - done
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method - done
    	if(s.isEmpty()) {//search for empty string
    		return false;
    	} 
    	else {
    		String sSearch = s.toLowerCase();
    		if(dict.contains(sSearch)) { //s is existing in dictionary
    			return true;
    		} 
    		else { //s is not in dictionary
    			return false;
    		}
    	}
    }	
}