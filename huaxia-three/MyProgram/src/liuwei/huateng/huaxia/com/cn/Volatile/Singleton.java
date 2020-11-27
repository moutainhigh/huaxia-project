/**
 * Title: Singleton.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月6日上午10:39:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

import java.net.Socket;

import com.sun.corba.se.pept.transport.Connection;

/**
 * @class_name:Singleton2019年12月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月6日上午10:39:19
 */
public class Singleton {
	private byte[] date = new  byte[1024];
	private static Singleton instance = null;
	 String  conn;
	 String socket;
	/**
	 * 
	 */
	public Singleton() {
		// TODO Auto-generated constructor stub
		this.conn  = "Hello World";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.socket = "Hello World!";
	}

	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年12月6日下午3:18:44
	 */
	private static Singleton getInstance()
	{
		if(null == instance){
			synchronized(Singleton.class){
				if(null == instance)
				{
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月6日上午10:39:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true){
			new Thread(new Runnable(){
				@Override
				public void run(){
					getInstance();
				}
			}).start();;
			new Thread(new Runnable(){
				@Override
				public void run(){
					while(instance != null){
						getInstance();
						System.out.println(instance.conn);
						System.out.println(instance.socket);
					}
				}
			}).start();;
			new Thread(new Runnable(){
				@Override
				public void run(){
					while(instance != null){
						getInstance();
						System.out.println(instance.conn);
						System.out.println(instance.socket);
					}
				}
			}).start();;
			new Thread(new Runnable(){
				@Override
				public void run(){
					while(instance != null){
						getInstance();
						System.out.println(instance.conn);
						System.out.println(instance.socket);
					}
				}
			}).start();;
			new Thread(new Runnable(){
				@Override
				public void run(){
					while(instance != null){
						getInstance();
						System.out.println(instance.conn);
						System.out.println(instance.socket);
					}
				}
			}).start();;
		}
	}
	
}
