/**
 * Title: MathRandomCha.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日上午11:20:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

/**
 * @class_name:MathRandomCha2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日上午11:20:09
 */
public class MathRandomCha {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日上午11:20:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("任意小写字符"+GetRandomChar('a','z'));
		System.out.println("任意大写字符"+GetRandomChar('A','Z'));
		System.out.println("0到9任意数字字符"+GetRandomChar('0','9'));

	}
	public static char GetRandomChar(char cha1,char cha2){
		return (char)(cha1+Math.random()*(cha2-cha1+1));
	}
}
