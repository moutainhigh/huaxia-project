/**
 * Title: OutClass2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日下午4:48:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:OutClass22020年9月23日
 * @comments:局部内部类的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日下午4:48:20
 */
public class OutClass2 {
	private static int a;
	private int b;
	public void partClassTest(final int c){
		final int d = 1;
		class PastClass{
			public void print(){
				System.out.println(c);
			}
		}
	}
	/**
	 * Constructor
	 */
	public OutClass2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日下午4:48:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
