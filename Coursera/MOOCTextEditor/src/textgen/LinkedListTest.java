package textgen;

public class LinkedListTest {

	public static void main(String[] args) {
		MyLinkedList<String> list = new MyLinkedList<String>();
		System.out.println("size is: " + list.size);
		list.add("toevoeging 1");
		list.add("toevoeging 2");
		list.add("toevoeging 3");
		list.add("toevoeging 4");
		//System.out.println("list size: " + list.size);
	}

}
