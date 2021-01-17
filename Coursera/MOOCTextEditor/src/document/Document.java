package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences 
	 * method here.  The reason we put countSyllables here because we'll 
	 * use it again next week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern 
	 * objects inside this method. Just use a loop to loop through the 
	 * characters in the string and write your own logic for counting 
	 * syllables.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 */
	protected int countSyllables(String word)
	{
		// TODO: Implement this method so that you can call it from the 
	    // getNumSyllables method in BasicDocument (module 2) and 
	    // EfficientDocument (module 3).
		List<String> vowels = Arrays.asList("a", "A", "e", "E", "i", "I", "o", "O", "u", "U", "y", "Y");
		int counter = 0; //each word has at least one syllable
		boolean isVowel = false;
		if(word.length() <= 3) {
			return 1; //word has only one or two characters, resulting in one Syllable
		}
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(vowels.contains("" + c))  { //c is vowel 
				if(word.length()== 1) { // a one character word 
					return 1;
				}
				if(!isVowel) { //previous char was not a vowel
					if(c == 'e' && ((i == word.length()-1))) { //c = 'e' and not the last character in a word
						return counter; // end of word with e, no increment
					}
					else {
						counter ++; //increment the amount of Syllables
						isVowel = true; // to prevent each vowel to trigger incrementing the counter
					}
				}
			}
			else {
				isVowel = false;
			}
		}
	    return counter;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
	    int numWords = this.getNumWords();
	    int numSentences = this.getNumSentences();
	    int numSyllables = this.getNumSyllables();
	    /* For test purposes. It is required to cast each devision to double to achieve the correct outcome
	    System.out.println("number of words: " + numWords);
	    System.out.println("number of Sentences: " + numSentences);
	    System.out.println("number of Syllables: " + numSyllables);
	    System.out.println("words/sentences * -1.015: " + (-1.015 * (numWords / numSentences)));
	    System.out.println("syllables/words: " + ((double)numSyllables/numWords));
	    System.out.println("syllables/words * -84.6: " + (-84.6 * ((double)numSyllables/numWords)));*/
	    double fleschScore = 206.835 - (1.015 * ((double)numWords / numSentences)) - (84.6 * ((double)numSyllables / numWords));
	    return fleschScore;
	}
	
	
	
}
