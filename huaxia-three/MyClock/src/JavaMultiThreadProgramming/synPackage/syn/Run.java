package JavaMultiThreadProgramming.synPackage.syn;
/**
 * 同步程序，只有一种可以执行，其他的不能执行
 * 一个synchronize方法执行执行一个，其他的阻塞
 * @author liwei
 *
 */
public class Run {
	public static void main(String args[]){
		ObjectService service = new ObjectService();
		ThreadA a = new ThreadA(service);
		a.setName("a");
		a.start();
		ThreadB b = new ThreadB(service);
		b.setName("b");
		b.start();
	}
}
