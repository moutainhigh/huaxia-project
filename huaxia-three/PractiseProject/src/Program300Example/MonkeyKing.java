/**
 * Title: MonkeyKing.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������9:18:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import javax.swing.JOptionPane;

/**
 * @class_name:MonkeyKing2020��9��22��
 * @comments:ѭ�����ֱ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������9:18:17
 */
public class MonkeyKing {

	/**
	 * Constructor
	 */
	public MonkeyKing() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��22������9:18:17
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String s;
		 int n,k,m,n1;
		 s = JOptionPane.showInputDialog("���������������");
		 n = Integer.parseInt(s);
		 n1 = n+1;
		 s = JOptionPane.showInputDialog("��������ʼ�������ӱ�ţ�");
		 k = Integer.parseInt(s);
		 s = JOptionPane.showInputDialog("�������������");
		 m = Integer.parseInt(s);
		 int a[] = new int[n+1];
		 a[0] = 0;
		 System.out.println("���ߵĺ��ӱ�ţ�");
		 for(int i=1;i<a.length;i++)
			 a[i] = 1;
			for(int i=1;i<=m;i++){
				if(n == 1)
					break;
				else if(i ==m ){
					n--;
					i =0;
					a[k] = 0;
					System.out.print(k+" ");
				}
				do{
					k++;
					k = k%n1;
				}while(a[k] != 1);
			}
			System.out.println("\n�������Ϊ��"+k);
		 }
	}
