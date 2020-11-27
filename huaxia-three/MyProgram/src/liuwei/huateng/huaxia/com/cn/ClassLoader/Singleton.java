/**
 * Title: Singleton.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��12������10:56:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ClassLoader;

/**
 * @class_name:Singleton2019��12��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��12������10:56:38
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
	 *@Date: 2019��12��12������10:58:23
	 */
	public static Singleton getInstance()
	{
		return instance;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��12������10:56:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton.x);
		System.out.println(singleton.y);
	}

}
