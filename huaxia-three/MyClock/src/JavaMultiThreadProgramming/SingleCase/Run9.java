package JavaMultiThreadProgramming.SingleCase;
/**
 * 静态内部类实现单例模式
 * @author liuwei
 *
 */
public class Run9 {
	public static void main(String args[]){
		MyThread9  t1 = new MyThread9();
		MyThread9 t2 = new MyThread9();
		MyThread9 t3 = new MyThread9();
		t1.start();
		t2.start();
		t3.start();
	}
}
