/**
 * Title: Program.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年11月12日下午12:50:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Program2019年11月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月12日下午12:50:03
 */
public class Program {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年11月12日下午12:50:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B b = new B();
	}
}
/**
 * 
 * @class_name:A2019年11月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月12日下午12:55:22
 */
class A
{
	/**
	 * 
	 * @param text
	 */
	public A(String text){
		System.out.println(text);
	}
}
/**
 * @class_name:B2019年11月12日
 * @comments: 先初始化变量，在执行构造方法
 * 先是分配变量内存，在执行方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年11月12日下午12:55:33
 */
class B
{
	static A a1 = new A("a1");
	A a2 = new A("a2");
	static 
	{
		a1 = new A("a3");
	}
	public B()
	{
		a2 = new A("a4");
	}
}
