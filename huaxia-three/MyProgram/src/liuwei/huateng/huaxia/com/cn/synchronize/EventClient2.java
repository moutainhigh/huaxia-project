/**
 * Title: EventClient.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��11������9:38:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:EventClient2019��12��11��
 * @comments:����wait��notiffy����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��11������9:38:47
 */
public class EventClient2 {

	/**
	 * 
	 */
	public EventClient2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��11������9:38:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final EventQueue2 eventQueue = new EventQueue2();
		for(int i=0;i<100;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					for(;;){
						eventQueue.offer(new EventQueue2.Event());
					}
				}
			},"Producer").start();
			new Thread(new Runnable(){
				@Override
				public void run(){
					for(;;){
						eventQueue.take();
						try {
							TimeUnit.MICROSECONDS.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			},"Consumer").start();
		}
		
	}

}
