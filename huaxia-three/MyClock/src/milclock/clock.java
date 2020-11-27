package milclock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class clock implements Runnable {

	@Override
	public void run() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(10);
				System.out.println("现在时间是： "+format.format(new Date()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
