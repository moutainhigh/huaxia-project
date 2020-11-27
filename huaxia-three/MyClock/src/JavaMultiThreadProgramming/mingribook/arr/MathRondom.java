/**
 * Title: MathRondom.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日上午11:13:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

/**
 * @class_name:MathRondom2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日上午11:13:01
 */
public class MathRondom {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日上午11:13:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("任意一个2-32之间的偶数："+ GetEvenNum(2,32));
	}
/**
 * @Title: GetEvenNum
 *@Description: TODO
 *@param num1
 *@param num2
 *@return
 *@author: LiuWei
 *@Date: 2019年10月14日上午11:16:08
 */
	public static int GetEvenNum(double num1,double num2){
		int s=(int)num1+(int)(Math.random()*(num2-num1));
		if(s%2==0){
			return s;
		}else
			return s+1;
	}
}
