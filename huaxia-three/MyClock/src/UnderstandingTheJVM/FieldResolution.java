package UnderstandingTheJVM;
/**
 * 测试一些接口和静态类
 * @author liuwei
 *
 */
public class FieldResolution {
	interface Interface0{
		int A =0;
	}
	interface Interface1 extends Interface0{
		int A =1;
	}
	interface Interface2{
		int A =2;
	}
	static class Parent implements Interface1{
		public static  int A =3;
	}
	static class Sub extends Parent implements Interface2{
		public static  int A = 4;
	}
	static class test implements Interface1{
		
	}
	public static void main(String args[]){
		System.out.println(Sub.A);
		System.out.println(Parent.A);
		System.out.println(test.A);
	}
	
}
