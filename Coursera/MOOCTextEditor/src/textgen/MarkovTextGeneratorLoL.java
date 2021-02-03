package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		List<String> words = getTokens(sourceText, "[a-zA-z.,!]+");
		starter = words.get(0);
		String prevWord = starter; //Word to save in new wordList with follow on wordt
		String w; //Word from words list at index 
		System.out.println("Method train - starter word is: " + starter); //TODO delete after testing
		int length = words.size();
		for(int i = 1; i < length; i++) { //check each word
			//System.out.println("word is: " + words.get(i)); TODO for testing purposes only
/*			if(i == length-1) {//if this is the last word in the list
				w = starter; // point to first word
			} else {*/
				w = words.get(i);
			//}
			int found = wordExists(prevWord);
			if(found >= 0) {
				ListNode ln = getNode(prevWord);
				System.out.println("Existing word " + ln.toString()); //TODO remove after test
				ln.addNextWord(w);
				wordList.set(found, ln);
				prevWord = w;
				System.out.println("Existing word with addition " + ln.toString()); //TODO remove after test
			} else {
				ListNode ln = new ListNode(prevWord);
				ln.addNextWord(w);
				wordList.add(ln);
				System.out.println("new word " +ln.toString()); //TODO remove after test
				prevWord = w;
			}
		}
		w = starter; //start linken eindwoord aan startwoord
		int found = wordExists(prevWord);
		if(found >= 0) {
			ListNode ln = getNode(prevWord);
			System.out.println("Existing word " + ln.toString()); //TODO remove after test
			ln.addNextWord(w);
			wordList.set(found, ln);
			System.out.println("Last word - Existing word with addition " + ln.toString()); //TODO remove after test
		} else {
			ListNode ln = new ListNode(prevWord);
			ln.addNextWord(w);
			wordList.add(ln);
			System.out.println("Last word - new word " +ln.toString()); //TODO remove after test
		}
		System.out.println("Content of wordList: " + wordList.size());
	}
	
	private ListNode getNode(String w) {
		// TODO Auto-generated method stub
		ListNode returnNode = new ListNode("");
		for(ListNode ln : wordList) {
			if(ln.getWord().equals(w)) {
				returnNode = ln;
			}
		}
		return returnNode;
	}


	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		return null;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
	}
	
	// TODO: Add any private helper methods you need here.
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String sourceText, String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(sourceText);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/**
	 * Method to check whether the current word is already in the wordList or not
	 * @param word
	 * @return
	 */
	private int wordExists(String word) {
		for(int i = 0; i < wordList.size(); i++) {
			ListNode ln = wordList.get(i);
			if(ln.getWord().equals(word)) {
				System.out.println("Methode wordExists, index is : " + i); //TODO delete after testing
				return i;
			}
		}
		return -1;
	}
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
//		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
//		String textString = "hi there hi Leo"; //Test voor simpele text
//		System.out.println(textString);
//		gen.train(textString);
//		System.out.println(gen);
//		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
//		gen.retrain(textString2);
		gen.train(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    return null;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


