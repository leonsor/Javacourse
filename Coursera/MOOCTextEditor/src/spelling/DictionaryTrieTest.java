package spelling;

public class DictionaryTrieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoCompleteDictionaryTrie trie = new AutoCompleteDictionaryTrie();
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
	}

}
