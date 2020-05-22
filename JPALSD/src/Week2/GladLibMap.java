package Week2;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
	
	private HashMap<String, ArrayList<String>> map;
	private ArrayList<String> catagories;
	/*private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;*/
	private ArrayList<String> usedWords;
	private ArrayList<String> usedCatagories;
	private int counter;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	//private static String dataSourceDirectory = "D:\\Users\\Leon\\Skydrive\\Development\\JPALSD\\data\\Week2\\datalong";//gebruiken voor desktop
	private static String dataSourceDirectory = "C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week2\\datalong";//gebruiken voor VAIO
	
	public GladLibMap(){
		catagories = new ArrayList<>(Arrays.asList("adjective","noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"));
		map = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		catagories = new ArrayList<>(Arrays.asList("adjective","noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"));
		map = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		for(int i = 0; i < catagories.size(); i++) { //new method for initializing the HashMap map
			String catagory = catagories.get(i);
			ArrayList<String> temp = new ArrayList<String>();
			temp = readIt(source + "/" + catagory +".txt");
			map.put(catagory, temp);
		}
		
		/*
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");*/
		usedWords = new ArrayList<String>();
		usedCatagories = new ArrayList<String>();
		counter = 0;
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		System.out.println(label);
		if (label.equals("number")){// no .txt file
			return ""+myRandom.nextInt(50)+5;
		}
		else {
			ArrayList<String> temp = map.get(label);
			return randomFrom(temp);
		}
	}
	/*
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}*/
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String catagory = w.substring(first+1,last);
		if(!usedCatagories.contains(catagory)) {//When catagory does not exist in ArrayList<>
			usedCatagories.add(catagory);
			System.out.println(catagory + " Toegevoegd aan ArrayList<> usedCatagories"); //TODO delete after printing 
		}
		String sub = getSubstitute(w.substring(first+1,last));
		String replacement = prefix+sub+suffix;
		int usedAlready = usedWords.indexOf(sub);
		while(usedAlready != -1) {
			sub = getSubstitute(w.substring(first+1,last));
			replacement = prefix+sub+suffix;
			usedAlready = usedWords.indexOf(sub);
			counter ++;
		}
		usedWords.add(sub);
		return replacement;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		//String story = fromTemplate("D:\\Users\\Leon\\Skydrive\\Development\\JPALSD\\data\\Week2\\data\\madtemplate2.txt");//use for Desktop
	    String story = fromTemplate("C:\\Users\\Leon\\git\\Javacourse\\JPALSD\\data\\Week2\\data\\madtemplate2.txt");//use for VAIO
		printOut(story, 60);
		System.out.println("\n" + "Total number of words replaced: " + usedWords.size());
		System.out.println("Total times already used words were replaced: " + counter);
		System.out.println("Total amount of words to choose from: " + this.totalWordsinMap());
		System.out.println("Total amount of words considered for this GladLib: " + this.totalWordsConsidered());
	}
	
	/**
	 * This method returns the total number of words in all the ArrayLists in the HashMap
	 */
	private int totalWordsinMap() {
		int total = 0;
		for(String catagory : map.keySet()) {
			int numWords = map.get(catagory).size();
			total = total + numWords;
			//System.out.println("Totaal aantal woorden: " + total);
		}
		return total;
	}
	
	/**
	 * This method returns the total number of words in the ArrayLists of the categories that were used 
	 * for a particular GladLib. If only noun, color, and adjective were the categories used in a GladLib, 
	 * then only calculate the sum of all the words in those three ArrayLists
	 */
	private int totalWordsConsidered() {
		int sum = 0;
		for(int i = 0; i < usedCatagories.size(); i++) {
			String catagory = usedCatagories.get(i);
			//System.out.println("Categorie om te zoeken " + catagory);//TODO delete after testing
			if(!catagory.equals("number")) {
				int amount = map.get(catagory).size();
				//System.out.println("amount is: " + amount);//TODO delete after testing
				sum = sum + amount;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		GladLibMap gL = new GladLibMap();
		gL.makeStory();
	}
}

