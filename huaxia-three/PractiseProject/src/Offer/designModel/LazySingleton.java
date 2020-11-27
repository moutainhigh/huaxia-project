/**
 * Title: LazySingleton.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月28日下午5:13:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:LazySingleton2020年9月28日
 * @comments: 懒汉模式，获取对象使用了枷锁，所以是线程安全的。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月28日下午5:13:29
 */
public class LazySingleton {
	private static LazySingleton instance;
	/**
	 * Constructor
	 */
	public LazySingleton() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午5:15:19
	 */
	public static synchronized  LazySingleton getInstance(){
		if(instance == null){
			try {
				Thread.sleep((int)Math.random()*100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			instance = new LazySingleton();
			
		}
		return instance;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午5:13:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(LazySingleton.getInstance().hashCode());
				}
				
			}).start();;
		}
	}

}
