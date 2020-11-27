package Algorithm;
/**
 * 设定线程的优先级
trHigh的循环次数为：1603057221
trLow的循环次数为：1553383148
 * @authorLiuwei
 */
class clicker extends Thread {
	private int click = 0;
	private boolean running = true;

	public int getClick() {
		return click;
	}

	public void run() {
		while (running)
			click = click + 1;
	}

	public void normalStop() {
		running = false;
	}
}

public class demoPri {
	public static void main(String args[]) {
		clicker trHigh, trLow;
		trHigh = new clicker();
		trLow = new clicker();
		trHigh.setPriority(Thread.NORM_PRIORITY + 4);
		trLow.setPriority(Thread.NORM_PRIORITY - 4);
		trLow.start();
		trHigh.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trHigh.normalStop();
		trLow.normalStop();
//		try {
//			trHigh.join();
//			trLow.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("trHigh的循环次数为：" + trHigh.getClick());
		System.out.println("trLow的循环次数为：" + trLow.getClick());
	}
}
