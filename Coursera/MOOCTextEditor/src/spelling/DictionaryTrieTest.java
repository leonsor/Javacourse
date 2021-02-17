package spelling;

public class DictionaryTrieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		AutoCompleteDictionaryTrie trie = new AutoCompleteDictionaryTrie();
		System.out.println("Woord a = woord: "+ trie.isWord("a"));
		trie.addWord("");		
		trie.addWord("ate");
		System.out.println("Trie size is: " + trie.size());
		System.out.print("Trie to String: ");
		trie.printTree();
		trie.addWord("Basecamp");
		System.out.println("Trie size is: " + trie.size());
		System.out.print("Trie to String: ");
		trie.printTree();
		trie.addWord("Base");
		System.out.println("Trie size is: " + trie.size());
		System.out.print("Trie to String: ");
		trie.printTree();
		trie.addWord("a");
		System.out.println("Trie size is: " + trie.size());
		System.out.print("Trie to String: ");
		trie.printTree();
		System.out.println("Woord a = woord: "+ trie.isWord("a"));
		System.out.println("Woord base = woord: "+ trie.isWord("base"));
		System.out.println("Woord nee = woord: "+ trie.isWord("nee"));*/

		AutoCompleteDictionaryTrie smallDict = new AutoCompleteDictionaryTrie();
		smallDict.addWord("Hello");
		System.out.println("Trie size is: " + smallDict.size());
		smallDict.addWord("HElLo");
		System.out.println("Trie size is: " + smallDict.size());
		smallDict.addWord("help");
		System.out.println("Trie size is: " + smallDict.size());
		smallDict.addWord("he");
		System.out.println("Trie size is: " + smallDict.size());
		smallDict.addWord("hem");
		System.out.println("Trie size is: " + smallDict.size());
/*		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		smallDict.addWord("subsequent"); */
		smallDict.printTree();
	}

}
