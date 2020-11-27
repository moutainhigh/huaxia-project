/**
 * Title: FinalClassDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午5:05:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.JavaNewToOld;

/**
 * @class_name:FinalClassDemo2020年8月6日
 * @comments:这是一个final类，它不能被继承，所以不可能有子类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午5:05:31
 */
public class FinalClassDemo {
	private String message = "这是一个final类";
	private String enable = "它不能被继承，所以不可能有子类";
	
	/**
	 * Constructor
	 */
	public FinalClassDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午5:05:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FinalClassDemo demo = new FinalClassDemo();//创建类的实例
		System.out.println(demo.message);
		System.out.println(demo.enable);
	}

}
