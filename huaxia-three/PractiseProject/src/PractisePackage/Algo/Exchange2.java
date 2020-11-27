/**
 * Title: Exchange.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��7������1:11:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Scanner;

/**
 * @class_name:Exchange2020��8��7��
 * @comments:̰���㷨������Ǯ���㷨 
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������1:11:12
 */
public class Exchange2 {
	private static int MAXN = 9;
	private static double parvalue[] = {100,50,20,10,5,2,1,0.5,0.2,0.1};
	static int num[] = new int[MAXN];
	/**
	 * Constructor
	 */
	public Exchange2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��7������1:11:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		float m;
		System.out.println("����������Ľ�");
		Scanner sc = new Scanner(System.in);
		 m = sc.nextFloat();
		 exchange(m);
		 System.out.printf("\n%.2fԪ��Ǯ����ɣ�\n",m);
		 for(i=0;i<MAXN;i++){
			 if(num[i]>0){
				 System.out.printf("%6.2f:%d��\n",(float)parvalue[i],num[i]);
			 }
		 }
	}
	/**
	 * @Title: exchange
	 *@Description: TODO ��������
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��7������1:45:59
	 */
	public static int exchange(double n){
		int i,j;
		for(i=0;i<MAXN;i++)
			if(n>parvalue[i] )break;
		while(n >0 && i<MAXN){
			if(n >= parvalue[i]){
				n-=parvalue[i];
				num[i]++;
			}else i++;
		}
		return 0;
	}
}
