package JavaMultiThreadProgramming.synPackage.JVM;

public class Service2 {
	public static void print(Object object){
		try{
			synchronized(object){
			while(true){
				System.out.println(Thread.currentThread().getName());
				Thread.sleep(1000);
			}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
