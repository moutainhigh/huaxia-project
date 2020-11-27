package UnderstandingTheJVM;
/**
 * 测试重载的方法,类型不同的重载方法
 * @author liuwei
 *
 */
public class StaticDispatch {
	static abstract class Human{
	}
	static class Man extends Human{
	}
	static class Woman extends Human{
	}
	public void sayHello(Human guy){
		System.out.println("hello,guy!");
	}
	public void sayHello(Man guy){
		System.out.println("hello,gentleman!");
	}
	public void sayHello(Woman guy){
		System.out.println("hello,lady!");
	}
	public static void main(String args[]){
		Human man=  new Man();
		Human woman = new Woman();
		Man man2=  new Man();
		Woman woman2 = new Woman();
		StaticDispatch sr =new StaticDispatch();
		sr.sayHello(man);
		sr.sayHello(woman);
		sr.sayHello(man2);
		sr.sayHello(woman2);
	}
}
