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
0
1
3
4
6
arry[]������5�������±�Խ����
û���쳣���гɹ��ˡ���
 * @param: catch�����쳣
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������10:37:02
 */
public class Abnormal3 {

	/**
	 * Constructor
	 */
	public Abnormal3() {
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
		int arry[] ={0,1,3,4,6};
		int a = 2;
		try{
			int z = 5/a;
			for(int i=0;i<10;i++){
				System.out.println(arry[i]);
			}
		}catch(ArithmeticException e){
			System.out.println("0������Ϊ��������");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("arry[]������5�������±�Խ����");
		}
		System.out.println("û���쳣���гɹ��ˡ�");
	}

}
