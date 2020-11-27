/**
 * Title: Process05.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日上午10:52:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process052020年8月18日
 * @comments:while 先判断，后执行，do..while，先执行一次，在判断
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日上午10:52:33
 */
public class Process05 {

	/**
	 * Constructor
	 */
	public Process05() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午10:52:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = 1;
		int num2 = 1;
		while(num1 <= 3){
			System.out.println("num1 == "+num1);
			num1++;
		}
		do{
			System.out.println("num2 == "+num2);
			num2++;
		}while(num2 <= 3);
	}
	
}
