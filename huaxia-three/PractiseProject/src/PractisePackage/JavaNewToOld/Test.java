/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午5:13:11
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.JavaNewToOld;
/**
 * @class_name:AbstractClass2020年8月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午5:15:41
 */
abstract class AbstractClass{
	static void say(){
		System.out.println("Hello World!");
	}
}
/**
 * @class_name:Test2020年8月6日
 * @comments:可以直接通过抽象类名调用其静态方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午5:13:11
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
	 *@Date: 2020年8月6日下午5:13:11
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractClass.say();
	}

}
