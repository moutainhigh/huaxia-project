/**
 * Title: MEthodTableTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日下午5:37:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

import java.lang.reflect.Method;

/**
 * @class_name:MEthodTableTest2019年12月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日下午5:37:18
 */
public class MEthodTableTest {

	/**
	 * 
	 */
	public MEthodTableTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月30日下午5:37:18
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Method square = MEthodTableTest.class.getMethod("square", double.class);
			Method sqrt = Math.class.getMethod("sqrt", double.class);
			printTable(1,10,10,square);
			printTable(1,10,10,sqrt);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @Title: printTable
	 *@Description: TODO
	 *@param from
	 *@param to
	 *@param n
	 *@param f
	 *@author: LiuWei
	 *@Date: 2019年12月30日下午5:39:15
	 */
	public static void printTable(double from,double to,int n,Method f){
		System.out.println(f);
		double dx =(to-from)/(n-1);
		for(double x= from;x<=to;x+=dx)
		{
			try{
				double y =(Double)f.invoke(null, x);
				System.out.printf("%10.4f|%10.4f%n",x,y);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * @Title: square
	 *@Description: TODO
	 *@param x
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年12月30日下午5:39:56
	 */
	public static double square(double x)
	{
		return x*x;
	}
}
