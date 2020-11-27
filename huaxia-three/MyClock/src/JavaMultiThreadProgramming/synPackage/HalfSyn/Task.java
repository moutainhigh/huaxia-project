package JavaMultiThreadProgramming.synPackage.HalfSyn;

public class Task {
	public void doLongTimeTask(){
		for(int i=0;i<100;i++){
			System.out.println("nosuynchronized threadName ="+Thread.currentThread().getName()+" i="+(i+1))	;
		}
		System.out.println("");
		synchronized(this){
			for(int i=0;i<100;i++){
				System.out.println("snchronized threadName ="+Thread.currentThread().getName()+" i="+(i+1))	;
			}
		}
	}
}
