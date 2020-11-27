package liuwei.huateng.huaxia.com.cn.ClassLoader;

public class Child extends Parent {
	static{
		System.out.println("The child is initialized!");
	}
	public static int x =10;
	public Child() {
		// TODO Auto-generated constructor stub
	}

}
