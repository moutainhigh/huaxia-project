/**
 * Title: Demo09.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������4:06:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Demo092020��8��18��
 * @comments: ����������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������4:06:39
 */
public class Demo09 {
	/**
	 * @Title: getAge
	 *@Description: TODO �ݹ�������
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��18������4:07:38
	 */
	public static int getAge(int n){
		if(n == 1)
			return 10;
		return 2+ getAge(n-1);
	}
	/**
	 * Constructor
	 */
	public Demo09() {
		// TODO Auto-generated constructor stub
	}
	

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������4:06:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("���������Ϊ"+getAge(5));
	}
}
