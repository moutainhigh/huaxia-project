/**
 * Title: Singleton1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月12日下午1:47:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Singleton12019年11月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月12日下午1:47:35
 */
public class Singleton1 {
	private static Singleton1 instance = null;
	private static Singleton1 Instance(){
		if(instance == null)
			instance = new Singleton1();
		return instance;
	}
	/**
	 * 
	 */
	public Singleton1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月12日下午1:47:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
}
