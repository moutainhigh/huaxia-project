/**
 * Title: Process03.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������10:27:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

import java.util.Scanner;

/**
 * @class_name:Process032020��8��18��
 * @comments: switch case������ڶ��if��䣬if����˫ѡ��switchcase���ڶ�ѡ��
 * @param: 
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������10:27:44
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
	 *@Date: 2020��8��18������10:27:44
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
	 *@Description: TODO �ж������ڼ�
	 *@param value
	 *@author: LiuWei
	 *@Date: 2020��8��18������10:28:30
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
