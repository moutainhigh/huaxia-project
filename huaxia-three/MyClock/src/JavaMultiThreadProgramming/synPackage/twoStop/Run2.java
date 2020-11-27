package JavaMultiThreadProgramming.synPackage.twoStop;

public class Run2 {
	public static void main(String args[]){
		Service2 service = new Service2();
		ThreadA2 athread = new ThreadA2(service);
		athread.start();
		ThreadB2 bthread = new ThreadB2(service);
		bthread.start();
	}
}
