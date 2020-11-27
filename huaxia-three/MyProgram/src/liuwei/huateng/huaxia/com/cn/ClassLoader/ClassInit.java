/**
 * Title: ClassInit.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��12������1:49:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ClassLoader;

/**
 * @class_name:ClassInit2019��12��12��
 * @comments: ˳��ִ�С�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��12������1:49:39
 */
public class ClassInit {
	/**
	 * 
	 * @class_name:Parent2019��12��12��
	 * @comments:
	 * @param:
	 * @return:
	 * @author ��ΰ/liuwei
	 * @createtime:2019��12��12������1:50:39
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
	 * @class_name:Child2019��12��12��
	 * @comments:
	 * @param:
	 * @return:
	 * @author ��ΰ/liuwei
	 * @createtime:2019��12��12������1:50:43
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
	 *@Date: 2019��12��12������1:49:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Child.i);
	}

}
