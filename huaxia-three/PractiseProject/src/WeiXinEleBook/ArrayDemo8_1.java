/**
 * Title: ArrayDemo8_1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������5:30:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

/**
 * @class_name:ArrayDemo8_12020��9��11��
 * @comments: �����ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������5:30:13
 */
public class ArrayDemo8_1 {

	/**
	 * Constructor
	 */
	public ArrayDemo8_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������5:30:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] month = {31,28,31,30,31,30,31,31,30,31,30,31};
		for(int i=0;i<month.length;i++){
			System.out.println("��"+(i+1)+"����"+month[i]+"��");
		}
	}

}
