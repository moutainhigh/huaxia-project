/**
 * Title: OuterClass.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������3:56:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:OuterClass2020��9��23��
 * @comments:��̬�ڲ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������3:56:27
 */
public class OuterClass {
	public static String className = "staticInnerClass";
	/**
	 * 
	 * @class_name:StaticInnerClass2020��9��23��
	 * @comments:
	 * @param:
	 * @return:
	 * @author ��ΰ/liuwei
	 * @createtime:2020��9��23������3:57:45
	 */
	public static class StaticInnerClass{
		/**
		 * @Title: getClassName
		 *@Description: TODO
		 *@author: LiuWei
		 *@Date: 2020��9��23������3:57:41
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
	 *@Date: 2020��9��23������3:56:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
		staticInnerClass.getClassName();
	}

}
