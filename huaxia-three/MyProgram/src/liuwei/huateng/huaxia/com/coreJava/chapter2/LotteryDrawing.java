/**
 * Title: LotteryDrawing.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������2:13:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @class_name:LotteryDrawing2019��12��26��
 * @comments: ���������������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������2:13:28
 */
public class LotteryDrawing {

	/**
	 * 
	 */
	public LotteryDrawing() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��26������2:13:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println(" How Many numbers do you need to draw?");
		int k = in.nextInt();
		System.out.println("What is the highest numbers you can draw?");
		int n =in.nextInt();
		int[] numbers = new int[n];
		for(int i=0;i<numbers.length;i++)
			numbers[i] = i+1;
		int[] result = new int[k];
		for(int i= 0;i<result.length;i++){
			//make a random index betweent 0 and n-1
			int r = (int)(Math.random()*n);
			//pick the element at the random location
			result[i] = numbers[r];
			//move the last element into the random location
			numbers[i] = numbers[n-1];
			n--;
		}
		//print the sorted array
		Arrays.sort(result);
		System.out.println("Bet the following combination. It will make you rich!")	;
		for(int r:result)
			System.out.println(r);
	}

}
