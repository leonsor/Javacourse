package module4;

public class Student extends Person{
	public Student() {
		this("student");
		System.out.println("#2  ");
	}
	public Student(String n) {
		super(n);
		System.out.print("#3 ");
	}
}