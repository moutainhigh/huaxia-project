/**
 * Title: Process01.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������10:05:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process012020��8��18��
 * @comments: ����if..else����  if..(if..else...)else...(if...else...)
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������10:05:55
 */
public class Process01 {

	/**
	 * Constructor
	 */
	public Process01() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������10:05:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(compare01(40,30)){
			System.out.println("40>30:true");
		}else{
			System.out.println("40>30:false");
		}
		if(compare01(10,20) && compare01(20,30)){
			System.out.println("��������");
		}else{
			System.out.println("����������");
		}
		if(compare01(20,10) || compare01(20,30)){
			System.out.println("��������");
		}else{
			System.out.println("����������");
		}
		if(compare02(1,1)){
			if(compare02(2,2)){
				System.out.println("Running....");
			}
		}
		if(compare01(1,2)){
			if(compare01(5,3)){
				System.out.println("5>3:true");
			}
		}
	}
	/**
	 * @Title: compare01
	 *@Description: TODO �ж��������Ĵ�С
	 *@param num1
	 *@param num2
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��18������10:07:47
	 */
	private static boolean compare01(int num1,int num2)
	{
		System.out.println("�жϣ�num1="+num1+";num2="+num2);
		return num1 > num2;
	}
	private static boolean compare02(int num1,int num2)
	{
		System.out.println("�жϣ�num1="+num1+";num2="+num2);
		return num1 == num2;
	}
}
