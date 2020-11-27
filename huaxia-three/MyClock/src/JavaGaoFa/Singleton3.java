package JavaGaoFa;
/**
 * 单例模式，没有频繁的创建销毁对象
 * @author liuwei
 *
 */
public class Singleton3 {
	private Singleton3(){
		System.out.println("SingleTon is create");
	}
	private static Singleton3 instance = new Singleton3();
	public static Singleton3 getInstance(){
		return instance;
	}
	public static void main(String args[]){
		//只创建一次
		Singleton3.getInstance();
		Singleton3.getInstance();
		Singleton3.getInstance();
		Singleton3.getInstance();
		Singleton3.getInstance();
		Singleton3.getInstance();
		Singleton3.getInstance();
	}
}
