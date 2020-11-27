package JavaGaoFa;
/**
 * 单例模式，没有频繁的创建销毁对象
 * @author liuwei
 *
 */
public class Singleton2 {
	public static void main(String args[]){
		//只创建一次
		Singleton.getInstance();
		Singleton.getInstance();
		Singleton.getInstance();
		Singleton.getInstance();
		Singleton.getInstance();
		Singleton.getInstance();
		Singleton.getInstance();
		System.out.println("---------------------------------------------------");
		System.out.println(Singleton.STATUS);
		System.out.println(Singleton.STATUS);
		System.out.println(Singleton.STATUS);
		System.out.println(Singleton.STATUS);
		System.out.println(Singleton.STATUS);
		System.out.println(Singleton.instance);
		
	}
}
 class Singleton{
	public static int STATUS = 1;
	private Singleton(){
		System.out.println("SingleTon is create");
	}
	 static Singleton instance = new Singleton();
	public static Singleton getInstance(){
		return instance;
	}
}
