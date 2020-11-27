/**
 * Title: OverLoadTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午2:05:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.arr;

/**
 * @class_name:OverLoadTest2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午2:05:50
 */
public class OverLoadTest {
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param a
	 *@param b
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:11:36
	 */
	public static int add(int a,int b){
		return a+b;
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param a
	 *@param b
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:11:39
	 */
	public static double add(double a,double b){
		return a+b;
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param a
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:11:43
	 */
	public static int add(int a){
		return a;
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param a
	 *@param b
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:11:47
	 */
	public static int add(int a,double b){
		return 1;
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param a
	 *@param b
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:11:51
	 */
	public static int add(double a,int b){
		return 2;
	}
	/**
	 * @Title: add
	 *@Description: TODO调用不定长方法
	 *@param a
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:17:34
	 */
	public static int add(int ... a){
		int s=0;
		for(int i=0;i<a.length;i++){
			s+=a[i];
		}
		return s;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:05:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("调用add(int,int)方法"+add(1,2));
		System.out.println("调用add(double,double)方法"+add(2.1,3.3));
		System.out.println("调用add(int)方法"+add(2));
		System.out.println("调用add(int,double)方法"+add(2,3.3));
		System.out.println("调用add(double,int)方法"+add(2.2,1));
		
		System.out.println("调用不定长方法"+add(1,2,3,4,5,6,7,8,9,10));

	}

}
