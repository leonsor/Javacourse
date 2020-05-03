/**
 * Try out video code for random numbers and Arrays
 */
package week1;

import java.util.Random;

/**
 * @author Leon
 *
 */
public class RandomNum {
	
	public void Simulate(int rolls) {
		Random rand = new Random();
		int[] numbers = new int[13];
		
		for(int k = 0; k< rolls; k++) {
			int d1 = rand.nextInt(6) + 1;
			int d2 = rand.nextInt(6) + 1;
			numbers[d1+d2] += 1;
		}
		for(int i = 2; i < numbers.length; i++) {
			System.out.println(i + "\'s=\t" + numbers[i] + "\t" + 100.0 * numbers[i]/rolls );
		}
	}
	
	public void testSimulate() {
		this.Simulate(10000);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomNum random = new RandomNum();
		random.testSimulate();

	}

}
