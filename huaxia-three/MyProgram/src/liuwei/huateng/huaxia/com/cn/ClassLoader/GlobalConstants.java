/**
 * Title: GlobalConstants.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月12日上午10:31:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ClassLoader;

import java.util.Random;

/**
 * @class_name:GlobalConstants2019年12月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月12日上午10:31:56
 */
public class GlobalConstants {
	static{
		System.out.println("The GlobalConstants will be initialized.");
	}
	public final static int MAX = 100;
	public final static int RANDOM = new Random().nextInt();
	/**
	 * 
	 */
	public GlobalConstants() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月12日上午10:31:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
