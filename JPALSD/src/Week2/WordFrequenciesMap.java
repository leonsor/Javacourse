/**
 * 
 */
package Week2;

import java.util.*;

import edu.duke.*;

/**
 * @author Leon
 *
 */
public class WordFrequenciesMap {

	public void countWords() {
		FileResource fr = new FileResource();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		//int total = 0;
		for(String w : fr.words()) {
			w = w.toLowerCase();
			if(map.keySet().contains(w)) {
				map.put(w,map.get(w) + 1);
			}
			else {
				map.put(w,1);
			}
		}
		for(String w : map.keySet()) {
			int occurrences = map.get(w);
			if(occurrences > 300) {
				System.out.println(occurrences + "\t" + w);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WordFrequenciesMap wFM = new WordFrequenciesMap();
		wFM.countWords();
	}

}
