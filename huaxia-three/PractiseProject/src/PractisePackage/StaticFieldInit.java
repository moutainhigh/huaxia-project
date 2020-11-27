package PractisePackage;
class A{
	static int value = 100;
	static{
		System.out.println("类A初始化!");
	}
}
class B extends A{
	static {
		System.out.println("类B初始化");
	}
}
public class StaticFieldInit {

	public StaticFieldInit() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(B.value);
	}

}
