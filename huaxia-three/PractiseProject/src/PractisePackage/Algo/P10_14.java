/**
 * Title: P10_14.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��13������10:03:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:P10_142020��8��13��
 * @comments:����˹̹��������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��13������10:03:13
 */
public class P10_14 {
	/**
	 * @Title: jieti
	 *@Description: TODO ���Խ���
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������10:07:10
	 */
	static int jieti(){
		int i,res;
		int count = 7;
		for(i = 1;i<100;i++){
			if((count%2 == 1)&&(count%3 == 2) && (count % 5 == 4) && (count %6 == 5)){
				res = count;
				break;
			}
			count = 7*(i+1);
		}
		return count;
	}
	/**
	 * Constructor
	 */
	public P10_14() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��13������10:03:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		System.out.println("����˹̹�����������");
		num = jieti();
		System.out.printf("��������ܹ���%d��̨��\n",num);
	}

}
