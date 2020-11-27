/**
 * Title: ClassInit.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月12日下午1:49:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ClassLoader;

/**
 * @class_name:ClassInit2019年12月12日
 * @comments: 顺序执行。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月12日下午1:49:39
 */
public class ClassInit {
	/**
	 * 
	 * @class_name:Parent2019年12月12日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年12月12日下午1:50:39
	 */
	static class Parent
	{
		static int value = 10;
		static {
			value = 20;
		}
	}
	/**
	 * 
	 * @class_name:Child2019年12月12日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年12月12日下午1:50:43
	 */
	static class Child extends Parent{
		static int i = value;
	}
	/**
	 * 
	 */
	public ClassInit() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月12日下午1:49:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Child.i);
	}

}
