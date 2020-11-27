/**
 * Title: ticker.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��7������2:45:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:ticker2020��8��7��
 * @comments:������һ�ֲ�Ʊ��ÿ��������7���������1-29��7�����ֲ�����ͬ��д�����еĺ������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������2:45:34
 */
public class ticker2 {
	private static int MAXN = 7;
	private static int NUM = 29;
	private static int num[] = new int[NUM];
	private static int lottery[]= new int[MAXN];
	/**
	 * Constructor
	 */
	public ticker2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��7������2:45:34
	 */
	public static void main(String[] args) {
		int i,j;
		for(i=0;i<NUM;i++){
			num[i] = i+1;
		}
		for(i=0;i<MAXN;i++)
			lottery[i] = 0;
		combine(NUM,MAXN);
		
	}
	/**
	 * @Title: combine
	 *@Description: TODO
	 *@param n�ݹ��� ��Ʊ����
	 *@param m
	 *@author: LiuWei
	 *@Date: 2020��8��7������3:21:55
	 */
	private static void combine(int n,int m){
		int i,j;
		for(i = n;i>=m ;i--){
			 lottery[m-1] = num[i-1];//����
			 if(m>1)
				 combine(i-1,m-1);
			 else{
				 for(j=MAXN-1;j>=0;j--)
					 System.out.printf("%3d", lottery[j]);
				 System.out.println();
			 }
		}
	}
}
