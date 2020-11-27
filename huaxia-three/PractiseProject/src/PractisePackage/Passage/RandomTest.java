/**
 * Title: RandomTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日下午3:55:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

import java.util.Random;

/**
 * @class_name:RandomTest2020年8月18日
 * @comments: Random 指定种子后每次执行的结果都是一样的。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日下午3:55:01
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
	 *@Date: 2020年8月18日下午3:55:01
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
