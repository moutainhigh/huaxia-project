/**
 * Title: ArrayMaxMin.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������9:34:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:ArrayMaxMin2020��9��18��
 * @comments:����������ֵ����Сֵ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������9:34:50
 */
public class ArrayMaxMin {

	/**
	 * Constructor
	 */
	public ArrayMaxMin() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������9:34:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i ,min,max;
		int A[] = {25,65,98,36,45};
		min = max = A[0] ;
		System.out.println("����A��Ԫ�ذ�����");
		for(i=0;i<A.length;i++){
			System.out.println(A[i] +" ");
			if(A[i]>max)
				max = A[i];
			if(A[i]<min)
				min = A[i];
		}
		System.out.println("��������ֵ��"+max);
		System.out.println("�������Сֵ��"+min);
	}

}
