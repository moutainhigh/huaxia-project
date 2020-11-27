package JavaMultiThreadProgramming.innerClass.volatile3;

public class Run {
	public static void main(String args[]){
		PrintString printStringService = new PrintString();
		new Thread(printStringService).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("我要停止它！ stopThread="+Thread.currentThread().getName());
		printStringService.setContinuePrint(false);
	}
}
