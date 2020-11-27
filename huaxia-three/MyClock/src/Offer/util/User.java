/**
 * Title: User.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月21日上午10:53:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @class_name:User2019年11月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月21日上午10:53:36
 */
public class User {

	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月21日上午10:53:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Contral control = new Contral();
		control.invoke(0);
	}
	public void use(Method method)
	{
		Tool  tool = new Tool();
		try {
			System.out.println(method.invoke(tool));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
