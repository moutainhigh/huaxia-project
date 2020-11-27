package testClock;

public class MyClock {

	public static void main(String args[]){
		Thread t1 = new Thread(new clock());
		t1.start();
	}
	
}
