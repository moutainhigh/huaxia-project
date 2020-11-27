package JavaMultiThreadProgramming.JOIN;

public class Test {
	public static void main(String args[]){
		MyThread threadTest = new MyThread();
		threadTest.start();
		System.out.println("我想当threadTest对象执行完再执行");
		System.out.println("但是上面的线程休眠时间是多少呢");
		System.out.println("答案是不确定吗");
	}
}
