/**
 * Title: Process05.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������10:52:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process052020��8��18��
 * @comments:while ���жϣ���ִ�У�do..while����ִ��һ�Σ����ж�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������10:52:33
 */
public class Process05 {

	/**
	 * Constructor
	 */
	public Process05() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������10:52:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = 1;
		int num2 = 1;
		while(num1 <= 3){
			System.out.println("num1 == "+num1);
			num1++;
		}
		do{
			System.out.println("num2 == "+num2);
			num2++;
		}while(num2 <= 3);
	}
	
}
