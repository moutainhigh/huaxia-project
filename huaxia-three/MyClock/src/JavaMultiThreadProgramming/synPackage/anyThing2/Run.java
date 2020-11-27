package JavaMultiThreadProgramming.synPackage.anyThing2;
/**
 * 必须是同一个对象，实现synchronize锁
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
