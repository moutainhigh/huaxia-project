package JavaMultiThreadProgramming.synPackage.JVM;

public class Run2 {
	public static void main(String args[]){
		Service2 service =new Service2();
		ThreadA2 a= new ThreadA2(service);
		a.setName("A");
		a.start();
		ThreadB2 b= new ThreadB2(service);
		b.setName("B");
		b.start();
	}
}
