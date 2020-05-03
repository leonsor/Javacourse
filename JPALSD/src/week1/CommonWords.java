/**
 * Modified class to count the CommonWords in Shakespears'plays
 */
package week1;

import edu.duke.*;

/**
 * @author Leon
 *
 */
public class CommonWords {

    public String[] getCommon(){
    	FileResource resource = new FileResource("data/common.txt");
		String[] common = new String[20];
		int index = 0;
		for(String s : resource.words()){
			common[index] = s;
			index += 1;
		}
		return common;
	}
		
	public int indexOf(String[] list, String word) {
	    for (int k=0; k<list.length; k++) {
	        if (list[k].equals(word)) {
	               return k;
	           }
	       }
	    return -1;
	}
		
	public void countWords(FileResource resource, String[] common, int[] counts){
		for(String word : resource.words()){
			word = word.toLowerCase();
			int index = indexOf(common,word);
			if (index != -1) {
				counts[index] += 1;
			}
		}
	}
	void countShakespeare(){
		String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
				          "likeit.txt", "macbeth.txt", "romeo.txt"};
	    //String[] plays = {"small.txt"};
		String[] common = getCommon();
		int[] counts = new int[common.length];
		for(int k=0; k < plays.length; k++){
			FileResource resource = new FileResource("data/" + plays[k]);
			countWords(resource,common,counts);
			System.out.println("done with " + plays[k]);
		}
			
		for(int k=0; k < common.length; k++){
			System.out.println(common[k] + "\t" + counts[k]);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommonWords common = new CommonWords();
		//common.testGetCommon(); //to test the getCommon words file
		common.countShakespeare();
	}
/**
 * Test method for getCommon where text files are stored in JPALSD/data/* 
 */
	public void testGetCommon() {
		String[] common = this.getCommon();
		for(int i = 0; i < common.length; i++) {
			System.out.println(common[i]);
		}
		
	}

}
