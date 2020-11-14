/**
 * 
 */
package week1;

/**
 * @author Leon
 *
 */
public class TestSimple {
	public void print() {  	
        Simple item = new Simple(3, "blue");     	
        System.out.println(item);  
        //System.out.println(item.mystery(5, "ho"));
   }      
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSimple tS = new TestSimple();
		tS.print();
	}

}
