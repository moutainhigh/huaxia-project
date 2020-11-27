/**
 * Title: Muex.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��9������10:57:43
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:Muex2019��12��9��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��9������10:57:43
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
	 *@Date: 2019��12��9������10:57:43
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
