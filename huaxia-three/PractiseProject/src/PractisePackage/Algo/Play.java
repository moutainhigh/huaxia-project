/**
 * Title: Play.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��7������3:48:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.util.Random;
import java.util.Scanner;

/**
 * @class_name:Play2020��8��7��
 * @comments:ģ���㷨
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������3:48:48
 */
public class Play {
	private static Random random = new Random();
	/**
	 * Constructor
	 */
	public Play() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��7������3:48:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int c;//��������
		int n;
		int i,m;
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("������������������0�˳���");
			n = sc.nextInt();
			if(n ==0) break;
			System.out.println("\n���뱾�ֲ��������� ����0�˳���");
			c = sc.nextInt();
			if(c ==0 ) break;
			for( i=0;i<c;i++){
				System.out.printf("\n��%dѡ�ֵ���������Ϊ:\n",i+i);
				play(n);
			}
		}while(true);
	}
	/**
	 * @Title: play
	 *@Description: TODO n�����ӵĵ���
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020��8��7������3:53:14
	 */
	public static void play(int n){
		int i,m=0,t=0;
		for(i =0;i<n;i++){
			t = random.nextInt(6)+1;
			m+=t;
			System.out.printf("\t��%d��:%d;\n",i+1,t);
		}
		System.out.printf("\t�ܵ���Ϊ:%d\n",m);
	}
}
