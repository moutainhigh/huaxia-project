/**
 * Title: HuHuanDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������10:38:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class_name:HuHuanDemo2020��9��22��
 * @comments:�����ʮ�����ݣ�ǰ����ͺ����Ԫ�ضԻ�
 * �������1������1
�������2������2
�������3������2
�������4������3
�������5������5
�������6������7
�������7������9
�������8������34
�������9������12
�������10������90
������������ǣ�
1 2 2 3 5 7 9 34 12 90 
�Ի���������ǣ�
90 12 34 9 7 5 3 2 2 1 
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������10:38:02
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
	 *@Date: 2020��9��22������10:38:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print();
	}
	/**
	 * @Title: write
	 *@Description: TODO
	 *@return ��������
	 *@author: LiuWei
	 *@Date: 2020��9��22������10:43:52
	 */
	public static int[] write(){
		BufferedReader[] buf = new BufferedReader[10] ;
		int n;
		int array[] = new int[10];
		for(int i=0;i<10;i++){
			buf[i] = new BufferedReader(new InputStreamReader(System.in));
			do{
				n = 1;
				System.out.print("�������"+(i+1)+"������");
				try{
					array[i] = Integer.parseInt(buf[i].readLine());
				}catch(NumberFormatException e){
					System.out.println("��������������������룺");
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
		System.out.println("\n������������ǣ�");
		for(int i =0;i<10;i++){
			System.out.print(ary[i] +" ");
		}
		for(int i =0;i<5;i++){
			s = ary[i] ;
			ary[i] = ary[9-i];
			ary[9-i] = s;
		}
		System.out.println("\n�Ի���������ǣ�");
		for(int i =0;i<10;i++){
			System.out.print(ary[i] +" ");
		}
		System.out.println();
	}
}
