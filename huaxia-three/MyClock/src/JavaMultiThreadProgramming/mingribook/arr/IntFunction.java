/**
 * Title: IntFunction.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日上午10:51:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

/**
 * @class_name:IntFunction2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日上午10:51:14
 */
public class IntFunction {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日上午10:51:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//返回第一个大于扥估参数的整数
		System.out.println("使用ceil()方法取整："+Math.ceil(5.2));
		//返回第一个小于等于参数的整数
		System.out.println("使用floor()方法取整："+Math.floor(2.5));
		//返回与参数最接近的整数
		System.out.println("使用rint()方法取整："+Math.rint(2.7));
		//返回与参数最接近的整数
		System.out.println("使用rint()方法取整："+Math.rint(2.5));
		//将参数加上0.5后返回最接近的整数，四舍五入，取较大的整数
		System.out.println("使用round()方法取整："+Math.round(3.4f));
		//将参数加上0.5后返回最接近的整数，四舍五入，取较大的整数
		System.out.println("使用round()方法取整："+Math.round(2.5));
	}

}
