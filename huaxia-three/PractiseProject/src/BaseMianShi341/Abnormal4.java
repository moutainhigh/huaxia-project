/**
 * Title: Abnormal.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������10:37:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:Abnormal2020��9��23��
 * @comments:
Z=2
Exception in thread "main" java.lang.NullPointerException
	at BaseMianShi341.Abnormal4.main(Abnormal4.java:48)
 * @param: catchû�в����쳣�����򷽷��������쳣��ֹ�ˡ�
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������10:37:02
 */
public class Abnormal4 {

	/**
	 * Constructor
	 */
	public Abnormal4() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��23������10:37:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 2;
		String b[] = new String[2];
		try{
			int z = 5/a;
			System.out.println("Z="+z);
			b[0].toLowerCase();
		}catch(ArithmeticException e){
			System.out.println("0������Ϊ��������");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("arry[]������5�������±�Խ����");
		}
		System.out.println("û���쳣���гɹ��ˡ�");
	}

}
