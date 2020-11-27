/**
 * Title: HuHuanDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日上午10:38:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class_name:HuHuanDemo2020年9月22日
 * @comments:数组的十个数据，前五个和后五个元素对换
 * 请输入第1个整数1
请输入第2个整数2
请输入第3个整数2
请输入第4个整数3
请输入第5个整数5
请输入第6个整数7
请输入第7个整数9
请输入第8个整数34
请输入第9个整数12
请输入第10个整数90
您输入的数组是：
1 2 2 3 5 7 9 34 12 90 
对换后的数组是：
90 12 34 9 7 5 3 2 2 1 
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日上午10:38:02
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
	 *@Date: 2020年9月22日上午10:38:02
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
	 *@Date: 2020年9月22日上午10:43:52
	 */
	public static int[] write(){
		BufferedReader[] buf = new BufferedReader[10] ;
		int n;
		int array[] = new int[10];
		for(int i=0;i<10;i++){
			buf[i] = new BufferedReader(new InputStreamReader(System.in));
			do{
				n = 1;
				System.out.print("请输入第"+(i+1)+"个整数");
				try{
					array[i] = Integer.parseInt(buf[i].readLine());
				}catch(NumberFormatException e){
					System.out.println("数据输入错误请重新输入：");
					n =0;
				}catch(Exception e){
					e.printStackTrace();
				}
			}while(n == 0);
		}
		return array;
	}
	public static void print(){
		int[] ary = write();
		int s;
		System.out.println("\n您输入的数组是：");
		for(int i =0;i<10;i++){
			System.out.print(ary[i] +" ");
		}
		for(int i =0;i<5;i++){
			s = ary[i] ;
			ary[i] = ary[9-i];
			ary[9-i] = s;
		}
		System.out.println("\n对换后的数组是：");
		for(int i =0;i<10;i++){
			System.out.print(ary[i] +" ");
		}
		System.out.println();
	}
}
