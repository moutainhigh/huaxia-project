package JavaMultiThreadProgramming.SingleCase;
/**
 * 静态内置类实现单例模式
 * @author liuwei
 *
 */
public class MyObject7 {
	//内部类方式
	public static class MyObjectHandler{
		private static MyObject7 myObject = new MyObject7();
	}
	private MyObject7(){
	}
	public static MyObject7 getInstance(){
		return MyObjectHandler.myObject;
	}
}
