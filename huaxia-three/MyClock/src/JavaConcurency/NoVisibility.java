package JavaConcurency;

public class NoVisibility {
	private static boolean ready;
	private static int number;
	private static class ReaderThead extends Thread{
		public void run(){
			 while(!ready)
				 Thread.yield();
			 System.out.println(number);
		}
	}
	public static void main(String args[]){
		new ReaderThead().start();
		number = 42;
		ready = true;
	}
}
