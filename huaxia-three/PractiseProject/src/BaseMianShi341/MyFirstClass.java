/**
 * Title: MyFirstClass.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日下午4:24:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:MyFirstClass2020年9月22日
 * @comments:
 * @param: 四舍五入的函数
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午4:24:18
 */
public class MyFirstClass {

	/**
	 * Constructor
	 */
	public MyFirstClass() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午4:24:18
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double v = 11.5;
		/**
		 * 11.0
			12.0
			12
			12
			11
		 */
		System.out.println(Math.floor(v));
		System.out.println(Math.ceil(v));
		System.out.println((int)Math.ceil(v));
		System.out.println(Math.round(v));
		System.out.println((int)Math.floor(v));
	}
}
