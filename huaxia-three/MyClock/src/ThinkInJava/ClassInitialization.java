package ThinkInJava;

import java.util.Random;
/**
 * 测试类的使用,静态类的使用
 * @author User
 *
 */
class Initable{
	static final int staticFinal = 47;
	static final int staticFinal2 = new Random(47).nextInt(1000);
	static{
		System.out.println("Initializing Initable");
	}
}
class Initable2{
	static final int staticFinal = 147;;
	static{
		System.out.println("Initializing Initable2");
	}
}
class Initable3{
	static final int staticFinal = 74;;
	static{
		System.out.println("Initializing Initable3");
	}
}
public class ClassInitialization {
	public static Random rand = new Random(47);
	public ClassInitialization() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[]){
		Class initable = Initable.class;
		System.out.println("After creating Initable ref");
		System.out.println(Initable.staticFinal);
		System.out.println(Initable.staticFinal2);
		System.out.println(Initable2.staticFinal);
		try {
			Class initable3 = Class.forName("Initable3");
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find Gum");
		}
		System.out.println("After creating Initable3 ref");
		System.out.println(Initable3.staticFinal);
	}
}
