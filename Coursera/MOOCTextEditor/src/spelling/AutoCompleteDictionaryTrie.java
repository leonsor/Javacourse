package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		if(word.isEmpty()|| this.isWord(word)) { //check for empty word. if empty, return false
//			System.out.println("word is empty or already exists, no action."); //TODO Delete after testing
			return false;
		} 
		String insertWord = word.toLowerCase();
//		System.out.println("Invoeg woord = : " + insertWord); //TODO delete after testing

		TrieNode start = root;
		for(int i = 0; i < insertWord.length(); i++) {
			char c = insertWord.charAt(i);
//			System.out.println("Character to add: " + c); // TODO delete after testing
			TrieNode next = start.getChild(c);
			if(next == null) {
//				System.out.println("Char nog niet in trie, nieuwe node gemaakt met char " + c);
				next = start.insert(c);
			}
			if(i == insertWord.length() - 1) {
//				System.out.println("einde van woord gevonden, woord is " + next.getText());
				next.setEndsWord(true);
				size += 1;
				return true;
			}
			start = next;
		}
	    return false; //word was already in the list, no new node is created
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s.isEmpty()) { //check for empty word. if empty, return false
//			System.out.println("word is empty, no action."); //TODO Delete after testing
			return false;
		}
		TrieNode start = root;
		String searchWord = s.toLowerCase();
		for(int i = 0; i < s.length(); i++) {
			if(start == null) {
				return false;
			}
			char c = searchWord.charAt(i);
//			System.out.println("Character to search: " + c); // TODO delete after testing
			TrieNode next = start.getChild(c);
			if(next != null) {
//				System.out.println("Char gevonden, zoeken naar volgend character " + c); //TODO delete after testing
				start = next;
				if(i == s.length() - 1 && start.endsWord()) {
//					System.out.println("Woord gevonden!");
					return true;
				}
			} 
			else {
				if(i == (s.length() - 1) && start.endsWord() && s.equals(start.getText())) {
//					System.out.println("Woord gevonden!");
					return true;
				}
//				System.out.println("Woord NIET gevonden!");
				return false;
			}
		}
//		System.out.println("Woord NIET gevonden!");
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 List<String> autoCompleteList = new LinkedList<String>();
    	 Queue<TrieNode> nodeQueue = new LinkedList<TrieNode>();
    	 TrieNode search = new TrieNode();
    	 TrieNode start = root; //Starting node to look for the stem
    	 String searchWord = prefix.toLowerCase(); //lower case search word
    	 System.out.println("Stem is " + searchWord); //TODO delete after testing
    	 if(searchWord.equals("") ) { //no character to start with, use root
    		 System.out.println("No character as search word, therefore use root node");
    		 nodeQueue.add(root);
    	 }
    	 for(int i = 0; i < prefix.length(); i++) { //looking for the stem
 			char c = searchWord.charAt(i);
 			System.out.println("Character to search: " + c); // TODO delete after testing
 			TrieNode next = start.getChild(c);
 			if(next != null) {
 				System.out.println("Char gevonden, zoeken naar volgend character van " + c); //TODO delete after testing
 				start = next;
 				if(i == searchWord.length() - 1 && searchWord.equals(start.getText())) { //if stem is found
 					System.out.println("Stem found, putting stem node in queue!");
 					search = start;
 					nodeQueue.add(search);
 				}
 			} 
 		}//end of finding the stem.
    	 
    	while(!nodeQueue.isEmpty() && (autoCompleteList.size() < numCompletions)) {
    		TrieNode curr = nodeQueue.remove();
    		if(curr != null) {
    			if(curr.endsWord()) {
    				autoCompleteList.add(curr.getText());
    				System.out.println("Content of autoCompletelist: " + autoCompleteList.toString()); //TODO Delete after testing
    			}
    			TrieNode child = new TrieNode();
    			Set<Character> set = curr.getValidNextCharacters();
    			Iterator<Character> it = set.iterator();
    			System.out.println("Characters in the iterator to find nodes with: ");
    			while(it.hasNext()  && autoCompleteList.size() < numCompletions) {
    				char c = it.next();
    				System.out.println("Character is: " + c);
    				child = curr.getChild(c);
    				nodeQueue.add(child);
    				System.out.println("Size of queue = " + nodeQueue.size());
    				//autoCompleteList.add(child.getText());
    			}
    			if(autoCompleteList.size() <= numCompletions) {
    				curr = child;
    			}
    		}
    	}
        return autoCompleteList;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}

 	/**
 	 * For printing nodes which are words only, in combination with printNodeWords()
 	 * To print all the nodes use printTree()
 	 */
	public void printTreeWords()
 	{
 		printNodeWords(root);
 	}
	
	/**
	 * To print a node which is marked as word only
	 * @param curr
	 */
 	public void printNodeWords(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		if(curr.endsWord()) {
 			System.out.println(curr.getText());
 		}
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNodeWords(next);
 		}
 	}
	
}