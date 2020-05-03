package main;

public class HelloWorld {
	static boolean answer = false;
	public static void main(String[] args) {
		
		answer = sleepIn(true, false);
		System.out.println(answer);
	}
	
	public static boolean sleepIn(boolean weekday, boolean vacation) {
		StringBuilder sb = new StringBuilder("Hello");
		if(!weekday || vacation) {
			return true;
		}
		
		return false;
		/*boolean answerReturn = false;
		if(weekday == true) {
			if(vacation == true) {
				answerReturn = true;
			}
			else {
				answerReturn = false;
			}
		}
		else {
			answerReturn = true;
		}
		return answerReturn;*/
	}
}
