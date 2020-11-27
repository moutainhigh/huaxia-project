package JavaMultiThreadProgramming.synPackage.test3;
/**
 * 两个方法执行同一个synchronized方法，没有顺序而言
 *
 */
public class Run {
	public static void main(String args[]){
		MyList list = new MyList();
		MyThreadA a = new MyThreadA(list);
		a.setName("A");
		a.start();
		MyThreadB b = new MyThreadB(list);
		b.setName("B");
		b.start();
	}
}
