package week2;

public class Debug1 {
	
	public void findAbc(String input) {
	    int index = input.indexOf("abc");
	    while (true) {
	        if (index == -1 || index >= input.length() - 3) {//index == -1) {
	            break;
	        }
	        System.out.println("Index: " +index);
	        String found = input.substring(index+1, index+4);
	        System.out.println(found);
	        index = input.indexOf("abc", index+3);
	        System.out.println("Index after updating: " +index);

	    }
	}
	   public void test() {
		   //findAbc("abcd");
		   //findAbc("abcdabc");
		   //findAbc("yabcyabc");
		   //findAbc("eusabce");
		   //findAbc("woiehabchi");
		   //findAbc("aaaaabc");
		   //findAbc("abcbbbabcdddabc");
		   //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
		   //findAbc("abcabcabcabca");
		   findAbc("kdabcabcjei");
	}

	public static void main(String[] args) {
		Debug1 db1 = new Debug1();
		db1.test();

	}

}
