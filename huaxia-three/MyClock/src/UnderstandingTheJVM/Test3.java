package UnderstandingTheJVM;
/**
 * 测试一些静态和属性类
 * @author liuwei
 *
 */
public class Test3 {
	static class Parent{
		public static int A = 1;
		static{
			A = 2;
		}
	}
	static class Sub extends Parent{
		public static int B = A;
	}
	public static void main(String args[]){
		System.out.println(Sub.B);
	}
}
