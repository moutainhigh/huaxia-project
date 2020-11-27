/**
 * Title: Singleton.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月15日下午2:24:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:Singleton2020年10月15日
 * @comments: 单例模式，创建一个模式
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月15日下午2:24:41
 */
public class Singleton {
	private static Singleton instance = new Singleton();
	/**
	 * Constructor
	 */
	public Singleton() {
		// TODO Auto-generated constructor stub
		System.out.println("Singleton is create");
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年10月15日下午2:26:22
	 */
	public static Singleton getInstance(){
		return instance;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月15日下午2:24:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10000;i++){
			System.out.println(Singleton.getInstance().hashCode());
		}
	}

}
