package PractisePackage;
class A{
	static int value = 100;
	static{
		System.out.println("��A��ʼ��!");
	}
}
class B extends A{
	static {
		System.out.println("��B��ʼ��");
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
