package UnderstandingTheJVM;
/**
 * 测试一些继承和静态的方法
 * @author liuwei
 *
 */
public class SuperClass {
	static{
		System.out.println("SuperClass init!");
	}
	public static int value = 123;
	public static void main(String args[]){
		System.out.println(SubClass.value);
	}
}
class SubClass extends SuperClass{
	static{
		System.out.println("Subclass init!");
	}
}
