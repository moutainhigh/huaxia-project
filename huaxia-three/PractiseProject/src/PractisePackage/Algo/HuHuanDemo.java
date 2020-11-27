/**
 * Title: HuHuanDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月13日下午3:07:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class_name:HuHuanDemo2020年8月13日
 * @comments:数组元素互换
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月13日下午3:07:49
 */
public class HuHuanDemo {

	/**
	 * Constructor
	 */
	public HuHuanDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午3:07:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print();
	}
	/**
	 * @Title: write
	 *@Description: TODO
	 *@return 输入数据
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午3:10:17
	 */
	public static int[] write(){
		BufferedReader[] buf = new BufferedReader[10];//申请缓冲数组
		int n;//开关变量
		int array[] = new int[10];
		for(int i=0;i<10;i++)
		{
			buf[i] = new BufferedReader(new InputStreamReader(System.in));
			do{
				n = 1;
				System.out.print("请输入第"+(i+1)+"个整数；");
				try {
					array[i] = Integer.parseInt(buf[i].readLine());
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					System.out.println("数据输入错误请重新输入");
					n=0;
					e.printStackTrace();
				}
			}while(n == 0);
		}
		return array;
	}
	/**
	 * @Title: print
	 *@Description: TODO输出数组
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午3:29:34
	 */
	public static void print(){
		int[] ary = write();
		int s;
		System.out.println("\n你输入的数组是：");
		for(int i=0;i<10;i++)
		{
			System.out.print(ary[i] +" ");
		}
		for(int i=0;i<5;i++)
		{
			s = ary[i];
			ary[i] = ary[9-i];
			ary[9-i] = s;
		}
		System.out.println("\n对换后的数组是：");
		for(int i=0;i<10;i++)
		{
			System.out.print(ary[i]+" ");
		}
		System.out.println();
	}
}
