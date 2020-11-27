package JavaMultiThreadProgramming.ThreadSafe.MultiSynchonized;

public class servoice {
	synchronized public void service1(){
		System.out.println("Service1");
		service2();
	}
	synchronized public void service2(){
		System.out.println("Service2");
		service3();
	}
	synchronized public void service3(){
		System.out.println("Service3");
	}
}
