package UnderstandingTheJVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试多线程的数据不同步的问题
 * 使用synchronized关键字实现线程同步
 * @author liuwei
 *
 */
public class Vector2 {
	private static List<Integer> vector = new ArrayList<Integer>();
	public static void main(String args[]){
		while(true){
			for(int i=0;i<10;i++)
				vector.add(i);
			Thread removeThread = new Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					synchronized(vector){
					for(int i=0;i<vector.size();i++)
						try{
							vector.remove(i);
							}catch(Exception e){
								e.printStackTrace();
								System.exit(0);
								}
					}
				}
			});
			Thread printThread =new  Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					synchronized(vector){
					for(int i=0;i<vector.size();i++)
						try{
						System.out.println(vector.get(i));
						}catch(Exception e){
							e.printStackTrace();
							System.exit(0);
						}
					}
				}
			});
			removeThread.start();
			printThread.start();
			
			while (Thread.activeCount()>10);
	}
	}
}
