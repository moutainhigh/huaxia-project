/**
 * Title: Muex.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月9日上午10:57:43
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:Muex2019年12月9日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月9日上午10:57:43
 */
public class Muex {
	private final static Object MUTEX = new Object();
	
	public void accessResourcece(){
		synchronized(MUTEX){
				try {
					TimeUnit.MINUTES.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}
	/**
	 * 
	 */
	public Muex() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月9日上午10:57:43
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Muex mutex = new Muex();
		for(int i=0;i<5;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					mutex.accessResourcece();
				}
			}).start();
		}
	}

}
