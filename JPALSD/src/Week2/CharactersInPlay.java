/**
 * Programming exercise part 2
 */
package Week2;

import java.util.*;
import edu.duke.*;

/**
 * @author Leon
 *
 */
public class CharactersInPlay {
	
	private ArrayList<String> characters;
	private ArrayList<Integer> counts;
	
	public CharactersInPlay() {
		characters = new ArrayList<String>();
		counts = new ArrayList<Integer>();
	}

	/**
	 * This method should update the two ArrayLists, adding the character’s 
	 * name if it is not already there, and counting this line as one 
	 * speaking part for this person. 
	 * @param person
	 */
	private void update(String person) {
		int index = characters.indexOf(person);
		if(index == -1) { //person is not yet in characters
			characters.add(person); //add person in characters ArrayList<>
			counts.add(1); //add in counts ArrayLilst<> with value 1
		}
		else { //person is already in characters
			counts.set(index, (counts.get(index) + 1));//add 1 to existing index
		}
	}
	
	/**
	 *  opens a file, and reads the file line-by-line. For each line, if there 
	 *  is a period on the line, extract the possible name of the speaking part, 
	 *  and call update to count it as an occurrence for this person
	 */
	private void findAllCharacters() {
		FileResource resource = new FileResource(); //select file to open
		for(String s : resource.lines()) { //for all lines in file
			int index = s.indexOf("."); //find the dot '.'
			if(index != -1) { //if found
				String text = s.substring(0, index);//create substring
				System.out.println("Derived name = " + text); //TODO delete after testing
				update(text); //call update() with substring 
			}//if no dot, continue with next line
		}
	}
	
	/**
	 * This method should print out the names of all those characters that have exactly 
	 * number speaking parts, where number is greater than or equal to num1 
	 * and less than or equal to num2
	 * @param num1
	 * @param num2
	 */
	private void charactersWithNumParts(int num1, int num2) {
		for(int i=0; i < counts.size(); i++) {
			int count = counts.get(i);
			if(count >= num1 && count <= num2) {
				System.out.println(characters.get(i) + ", Character has " + 
						counts.get(i) + " speaking parts");
			}
		}
	}
	
	/**
	 * This method should call findAllCharacters, and then for each main character, 
	 * print out the main character, followed by the number of speaking parts 
	 * that character has. A main character is one who has more speaking parts than 
	 * most people.
	 */
	private void tester() {
		findAllCharacters();
		/*for(int i= 0; i < counts.size(); i++) {
			if(counts.get(i) > 2 ) {
				System.out.println(characters.get(i) + ", Character has " + 
						counts.get(i) + " speaking parts");
			}
		}*/
		charactersWithNumParts(10, 15);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CharactersInPlay charIP = new CharactersInPlay();
		charIP.tester();
	}

}
