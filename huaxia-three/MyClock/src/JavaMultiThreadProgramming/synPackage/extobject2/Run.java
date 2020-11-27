package JavaMultiThreadProgramming.synPackage.extobject2;
/**
 * 锁对象和锁方法是并级的，并且执行顺序是依次进行
 * @author liuwei
 *
 */
public class Run {
	public static void main(String args[]){
		Service service = new Service();
		MyObject object =new MyObject();
		ThreadB b =new ThreadB(object);
		b.setName("b");
		b.start();
		ThreadA a= new ThreadA(service,object);
		a.setName("a");
		a.start();
	}
}
