/**
 * Title: Test6.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������10:44:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:Test62019��12��26��
 * @comments: ���������ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������10:44:37
 */
public class Test6 {

	/**
	 * 
	 */
	public Test6() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��26������10:44:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[100];
		for(int i=0;i<100;i++)
			a[i] = i;
		for(int i=0;i<100;i++){
			System.out.print(i+" ");
		}
		System.out.println();
		String[] names = new String[10];
		for(int i=0;i<10;i++)
			names[i] = new String("Hello Word!"+i);
		for(int i=0;i<10;i++)
			System.out.print(names[i]);
		System.out.println();
	}

}
