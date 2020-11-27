package Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test001 {

	private static int allThreadCount = 0;
	public static void main(String[] args) {
		int count = 200;
		for(int i=1; i<= 40; i++){
			System.out.print("主线程 "+Thread.currentThread().getId()+"， ");
			System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss， ").format(new Date()));
			System.out.print("第"+i+"次压力， ");
			
			if(i<=21){
				count = count*2;
			}else{
				count = count/2;
			}
			try {
				newSomeThread(count);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static void newSomeThread(int n){
		allThreadCount = allThreadCount + n;
		System.out.println("创建 "+n+" 个子线程，总共创建了 "+allThreadCount+" 个线程");
		for(int i=1; i<=n; i++){
			if(i<=n-1){
				new Thread(new Runnable(){
					int i=0;
					@Override
					public void run() {
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						while(true){
							if(i >100000){
								break;
							}
							i++;
						}
					}}).start();
			}else{
				new Thread(new Runnable(){
					int i=0;
					@Override
					public void run() {
//						try {
//							Thread.sleep(1);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						while(true){
							if(i >100000){
								break;
							}
							i++;
						}
					}}).start();
			}
		}
	}

}
