package spelling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class AutoCompleteDictionaryTrieTest {
	
/**
 * A self designed class to test and debug the Autocomplete function before running the JUnit tests
 * @param args
 */

	public static void main(String[] args) {
		AutoCompleteDictionaryTrie smallDict = new AutoCompleteDictionaryTrie(); //create new dictionary
		System.out.println("------------------------Set up dictionary------------------------");
		smallDict.addWord("hello");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("hem");
		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		System.out.println("Size of dictionary should be 7, is actually: " + smallDict.size());
		System.out.println("------------------------Print Words in tree-------------------");
		smallDict.printTreeWords();
		System.out.println("------------------------End of Set up ------------------------");
		
		List<String> completions;
/*		completions = smallDict.predictCompletions("", 0);
		System.out.println("Completions should be 0, actual size is: "+ completions.size());*/
		
		completions = smallDict.predictCompletions("hot",  4);
		System.out.println("Completions should be 4, actual size is: "+ completions.size());
		System.out.println("--------------------List of completions found ----------------");
		for(String s : completions) {
			System.out.println(s);
		}
	}
}
