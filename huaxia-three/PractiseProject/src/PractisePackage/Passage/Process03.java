/**
 * Title: Process03.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日上午10:27:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

import java.util.Scanner;

/**
 * @class_name:Process032020年8月18日
 * @comments: switch case语句用于多个if语句，if用于双选择，switchcase用于多选择
 * @param: 
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日上午10:27:44
 */
public class Process03 {

	/**
	 * Constructor
	 */
	public Process03() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午10:27:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("What day is it today:");
		String value = scan.next();
		weekInfo(value);
	}
	/**
	 * @Title: weekInfo
	 *@Description: TODO 判断是星期几
	 *@param value
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午10:28:30
	 */
	private static void weekInfo(String value){
		switch(value){
			case "Monday":
				System.out.println("Monday");
				break;
			case "Tuesday":
				System.out.println("Tuesday");
				break;
			case "Wednesday":
				System.out.println("Wednesday");
				break;
			case "Thursday":
				System.out.println("Thursday");
				break;
			case "Friday":
				System.out.println("Friday");
				break;
			case "Saturday":
				System.out.println("Saturday");
				break;
			case "Sunday":
				System.out.println("Sunday");
				break;
			default:
				System.out.println("Matching failure");
				break;
		}
	}
}
