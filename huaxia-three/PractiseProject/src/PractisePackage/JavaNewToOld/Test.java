/**
 * Title: Test.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��6������5:13:11
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.JavaNewToOld;
/**
 * @class_name:AbstractClass2020��8��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������5:15:41
 */
abstract class AbstractClass{
	static void say(){
		System.out.println("Hello World!");
	}
}
/**
 * @class_name:Test2020��8��6��
 * @comments:����ֱ��ͨ���������������侲̬����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������5:13:11
 */
public class Test {
	
	/**
	 * Constructor
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��6������5:13:11
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractClass.say();
	}

}
