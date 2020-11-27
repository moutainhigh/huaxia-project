/**
 * Title: VolatileFoo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午4:53:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:VolatileFoo2019年12月5日
 * @comments:voliatile 会实时刷新
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午4:53:29
 */
public class VolatileFoo {

	final static int MAX = 5;
	static int init_value =0;
	/**
	 * 
	 */
	public VolatileFoo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午4:53:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new Runnable(){
			@Override
			public void run(){
				int localValue = init_value;
				while(localValue < MAX){
					if(init_value != localValue){
						System.out.printf("The init_value is update to [%d]\n",init_value);
						localValue = init_value;
					}
				}
			}
		},"Reader").start();
		//update 线程对值进行改变
		new Thread(new Runnable(){
			@Override
			public void run(){
				int localValue = init_value;
				while(localValue < MAX){
					
						System.out.printf("The init_value is change to [%d]\n",++localValue);
						  init_value  = localValue;
					
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		},"Updater").start();
	}

}
