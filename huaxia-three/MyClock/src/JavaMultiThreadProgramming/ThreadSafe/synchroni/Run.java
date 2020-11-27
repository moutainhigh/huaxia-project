package JavaMultiThreadProgramming.ThreadSafe.synchroni;

public class Run {
	public static void main(String[] args){
		MyObject myobjectd = new MyObject();
		ThreadA athread = new ThreadA(myobjectd);
		athread.setName("A");
		athread.start();
		ThreadB bthread = new ThreadB(myobjectd);
		bthread.setName("B");
		bthread.start();
	}
}
