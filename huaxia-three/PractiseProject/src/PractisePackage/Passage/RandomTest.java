/**
 * Title: RandomTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������3:55:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

import java.util.Random;

/**
 * @class_name:RandomTest2020��8��18��
 * @comments: Random ָ�����Ӻ�ÿ��ִ�еĽ������һ���ġ�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������3:55:01
 */
public class RandomTest {
	
	/**
	 * Constructor
	 */
	public RandomTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO 
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������3:55:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		for(int i = 0;i<10;i++){
			System.out.printf(r.nextInt()+" ");
		}
		System.out.println();
		Random r2 = new Random(10);
		for(int i = 0;i<10;i++){
			System.out.printf(r2.nextInt()+" ");
		}
		System.out.println();
	}

}
