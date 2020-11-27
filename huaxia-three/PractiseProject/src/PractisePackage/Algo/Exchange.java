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
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������1:11:12
 */
public class Exchange {
	private static int MAXN = 9;
	private static int parvalue[] = {10000,5000,2000,1000,500,200,100,50,20,10};
	static int num[] = new int[MAXN];
	/**
	 * Constructor
	 */
	public Exchange() {
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
		 exchange((int)(100*m));
		 System.out.printf("\n%.2fԪ��Ǯ����ɣ�\n",m);
		 for(i=0;i<MAXN;i++){
			 if(num[i]>0){
				 System.out.printf("%6.2f:%d��\n",(float)parvalue[i]/100.0,num[i]);
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
	public static int exchange(int n){
		int i,j;
		for(i=0;i<MAXN;i++)
			if(n>parvalue[i] )break;
		while(n >0 && i<MAXN){
			if(n >= parvalue[i]){
				n-=parvalue[i];
				num[i]++;
			}else if(n<10&&n>=5){
				num[MAXN -1]++;
				break;
			}else i++;
		}
		return 0;
	}
}
