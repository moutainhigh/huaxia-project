/**
 * Title: Test6.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月26日上午10:44:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:Test62019年12月26日
 * @comments: 测试数组的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月26日上午10:44:37
 */
public class Test6 {

	/**
	 * 
	 */
	public Test6() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月26日上午10:44:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[100];
		for(int i=0;i<100;i++)
			a[i] = i;
		for(int i=0;i<100;i++){
			System.out.print(i+" ");
		}
		System.out.println();
		String[] names = new String[10];
		for(int i=0;i<10;i++)
			names[i] = new String("Hello Word!"+i);
		for(int i=0;i<10;i++)
			System.out.print(names[i]);
		System.out.println();
	}

}
