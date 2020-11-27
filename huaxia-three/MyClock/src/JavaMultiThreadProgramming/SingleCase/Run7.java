package JavaMultiThreadProgramming.SingleCase;
/**
 * 静态内部类实现单例模式
 * @author liuwei
 *
 */
public class Run7 {
	public static void main(String args[]){
		MyThread7  t1 = new MyThread7();
		MyThread7 t2 = new MyThread7();
		MyThread7 t3 = new MyThread7();
		t1.start();
		t2.start();
		t3.start();
	}
}
