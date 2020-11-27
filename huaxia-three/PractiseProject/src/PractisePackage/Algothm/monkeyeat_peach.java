/**
 * Title: monkeyeat_peach.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月17日下午3:50:30
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algothm;

import java.util.Scanner;

/**
 * @class_name:monkeyeat_peach2020年8月17日
 * @comments:递归求解猴子吃桃问题，每一天都池一半加一个，第10天之后1个桃子
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月17日下午3:50:30
 */
public class monkeyeat_peach {

	/**
	 * Constructor
	 */
	public monkeyeat_peach() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午3:50:30
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int days;
		long sum;
		System.out.println("输入天数：");
		Scanner input = new Scanner(System.in);
		days = input.nextInt();
		sum = peach(days);
		System.out.println("最初的桃子数目："+sum);
	}
	/**
	 * @Title: peach
	 *@Description: TODO 递归求解猴子吃桃问题，每一天都池一半加一个，第10天之后1个桃子
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月17日下午3:51:24
	 */
	public static int peach(int n)
	{
		if(n == 1)
			return 1;
		else 
			return (peach(n-1)+1)*2;
	}
}
