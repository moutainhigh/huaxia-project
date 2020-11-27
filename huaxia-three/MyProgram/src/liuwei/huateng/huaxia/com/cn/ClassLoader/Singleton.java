/**
 * Title: Singleton.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月12日上午10:56:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ClassLoader;

/**
 * @class_name:Singleton2019年12月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月12日上午10:56:38
 */
public class Singleton {
	private static Singleton instance = new Singleton();
	private static int x =0;
	private static int y;
	private Singleton()
	{
		x++;
		y++;
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年12月12日上午10:58:23
	 */
	public static Singleton getInstance()
	{
		return instance;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月12日上午10:56:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton.x);
		System.out.println(singleton.y);
	}

}
