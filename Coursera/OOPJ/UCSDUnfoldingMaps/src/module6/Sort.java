/**
 * 
 */
package module6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Leon
 * Date: 3 JAN 2021
 * Class constructed during the lesson on sorting in week 6 OOPJ
 */
public class Sort {

	public static int[] listOrigin = {3, 8, 4, 9, 22, 1, 32, 23}; 
	
	public void printList(int[] list) {
		for(int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}
	
	/**
	 * Selection Sort Algorithm which puts the smallest number first, than looks at each 
	 * remaining part of the array for the next smallest number and swaps it with the 
	 * reference number.
	 * @param l is an unsorted list.
	 */
	public void sortList(int[] l) {
		int indexMin;
		for(int i = 0; i < l.length-1; i++) {
			int tempValue = l[i];//store value of index i in tempValue
			indexMin = i;
			System.out.println("Tijdelijke waarde: " + l[i]);
			for(int index = i+1; index < l.length; index ++) {//check between i+1 - lenght-1 what the smallest element is
				if(l[index] <= l[indexMin]) {
					System.out.println("Kleinere waarde gevonden: " + l[index]);
					indexMin = index;
				}
			}
			swap(l, i, indexMin);
			/*l[i] = l[indexMin];// swap smallest element at index s to index i+1
			l[indexMin] = tempValue;*/
		}
	}
	
	/**
	 * Insertion sort algorithm which looks at pairs and swaps the members of the pair
	 * Often a lot faster than the previous search algorithm
	 * @param list is an unsorted list
	 */
	public static void misterySort(int[] list) {
		int currInd;
		for(int pos = 1; pos < list.length-1; pos++) {
			currInd = pos;
			while(currInd > 0 && list[currInd] < list[currInd-1]) {
				swap(list, currInd, currInd-1);
			}
		}
	}
	
	private static void swap(int[] list, int indexOld, int indexNew) {
		int tempValue = list[indexOld];
		list[indexOld] = list[indexNew];// swap smallest element at index s to index i+1
		list[indexNew] = tempValue;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sort s = new Sort();
		
		s.printList(listOrigin);//print original array
		int[] listSorted = listOrigin; //copy original to listSorted
		s.sortList(listSorted); //subroutine to sort new list -> verbose output
		s.printList(listSorted); //print new sorted list
		/********Gebruik van nieuw algoritme waarbij nummerparen worden vergeleken***/
		int[] misteryList = listOrigin;
		Sort.misterySort(misteryList);
		s.printList(misteryList);
		/*******************Gebruik van de ingebouwde functie Collections sort ******/
		List<Integer> toSort = new ArrayList<Integer>();
		for(int i = 0; i < listOrigin.length; i++) {
			toSort.add(listOrigin[i]);
		}
		Collections.sort(toSort);
		System.out.println(toSort.toString());
	}

}
