/**
 * Title: OuterClass.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日下午3:56:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:OuterClass2020年9月23日
 * @comments:静态内部类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日下午3:56:27
 */
public class OuterClass {
	public static String className = "staticInnerClass";
	/**
	 * 
	 * @class_name:StaticInnerClass2020年9月23日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年9月23日下午3:57:45
	 */
	public static class StaticInnerClass{
		/**
		 * @Title: getClassName
		 *@Description: TODO
		 *@author: LiuWei
		 *@Date: 2020年9月23日下午3:57:41
		 */
		public void getClassName(){
			System.out.println("className"+className);
		}
	}
	/**
	 * Constructor
	 */
	public OuterClass() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日下午3:56:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
		staticInnerClass.getClassName();
	}

}
