/**
 * Title: Singleton.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日上午9:51:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Singleton2020年9月29日
 * @comments:SingletonHolder静态内部类在JVM是唯一的。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日上午9:51:16
 */
public class Singleton {
	/**
	 * 
	 * @class_name:SingletonHolder2020年9月29日
	 * @comments:SingletonHolder静态内部类在JVM是唯一的。
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年9月29日上午9:53:10
	 */
	private static class SingletonHolder{
		private static final Singleton INSTANCE = new Singleton();
		public SingletonHolder(){};
		public static final Singleton getInstance(){
			return SingletonHolder.INSTANCE;
		}
	}
	/**
	 * Constructor
	 */
	public Singleton() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月29日上午9:51:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Singleton.SingletonHolder.getInstance().hashCode());
				}
				
			}).start();;
		}
	
	}

}
