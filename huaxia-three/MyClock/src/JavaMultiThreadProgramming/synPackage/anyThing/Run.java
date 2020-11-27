package JavaMultiThreadProgramming.synPackage.anyThing;
/**
 * 任意对象实现synchronize锁
 * @author liwei
 *
 */
public class Run {
	public static void main(String args[]){
		Service service = new Service();
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
	}
}
