/**
 * Title: StaticCodeBlock.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午5:19:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

/**
 * @class_name:StaticCodeBlock2020年9月11日
 * @comments: 构造方法块和静态方法块执行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午5:19:56
 */
public class StaticCodeBlock {
	static{
		System.out.println("静态代码块执行。。。。。。");
	}
	/**
	 * Constructor
	 */
	public StaticCodeBlock() {
		// TODO Auto-generated constructor stub
		System.out.println("构造方法执行");
	}
	{
		System.out.println("构造方法执行.....");
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午5:19:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main方法开始执行");
		System.out.println("创建第一个对象");
		new StaticCodeBlock();
		System.out.println("创建第二个对象");
		new StaticCodeBlock();
		System.out.println("创建第三个对象");
		new StaticCodeBlock();
	}
}
