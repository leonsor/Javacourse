package textgen;

public class LinkedListTest {

	public static <E> void main(String[] args) {
		MyLinkedList<String> list = new MyLinkedList<String>();
		System.out.println("size is: " + list.size);
		list.add("toevoeging 1");//index 1
		list.add("toevoeging 2");//index 2
		list.add("toevoeging 3");//index 3
		list.add("toevoeging 4");//index 4
		System.out.println("list size: " + list.size);
		list.add(2, "tussenvoeging voor 3");//tussen 2 en 3 invoegen!!!
		list.add(0, "Tussenvoeging op 0");//aan het begin invoegen!!!
//		list.add(6, "foute tussenvoeging");//moet IndexOutofBoundserror geven!!!!
		for(int i = 0; i < list.size; i++) {
			System.out.println("Element data= " + list.get(i));
		}
		System.out.println(list.toString());
	    MyLinkedList<String> shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		shortList.add(1, "BB");
		System.out.println(shortList.toString());
		String data = shortList.set(2, "CC");
		System.out.println(shortList.toString());
		System.out.println("Deleted element was " + data);
		System.out.println("Element to delete at index " + 1 + " is: " + shortList.get(1));
		shortList.remove(1);
		System.out.println(shortList.toString());
	}

}
