/**
 * Title: JudgeSeason.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午3:27:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:JudgeSeason2020年9月18日
 * @comments:switch case语句的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午3:27:44
 */
public class JudgeSeason {

	/**
	 * Constructor
	 */
	public JudgeSeason() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午3:27:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入一个月份（仅输入数字），我来告诉你它属于哪个季节：");
		int month = scan.nextInt();
		switch(month){
		case 12:
		case 1:
		case 2:
			System.out.println("您输入的月份属于冬季。");
			break;
		case 3:
		case 4:
		case 5:
			System.out.println("您输入的月份属于春季。");
			break;
		case 6:
		case 7:
		case 8:
			System.out.println("您输入的月份属于夏季。");
			break;
		case 9:
		case 10:
		case 11:
			System.out.println("您输入的月份属于秋季。");
			break;
		}
	}

}
