/**
 * Title: fibnacci.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日下午2:34:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:fibnacci2020年8月18日
 * @comments: 迭代循环的方法更短，相对于递归来说
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日下午2:34:44
 */
public class fibnacci {

	/**
	 * Constructor
	 */
	public fibnacci() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日下午2:34:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start,end;
		start = System.currentTimeMillis();
		calFibnacciByRecursive(40);
		end = System.currentTimeMillis();
		System.out.println("calFibnacciByRecursive执行时间"+(end-start));
		start = System.currentTimeMillis();
		calFibnoacciByLoop(40);
		end = System.currentTimeMillis();
		System.out.println("calFibnoacciByLoop执行时间"+(end-start));
	}
	/**
	 * @Title: calFibnacciByRecursive
	 *@Description: TODO 递归算法
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月18日下午2:36:22
	 */
	public static long calFibnacciByRecursive(long n){
		if(n == 1){
			return 1;
		}else if(n == 2){
			return 1;
		}
		return calFibnacciByRecursive(n-2)+ calFibnacciByRecursive(n-1);
	}
	/**
	 * @Title: calFibnoacciByLoop
	 *@Description: TODO 迭代算法
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月18日下午2:38:03
	 */
	public  static long calFibnoacciByLoop(long n){
		long n1 = 1;
		long n2 = 1;
		long n3 = 0;
		for(int i=0;i<n;i++){
			n3 = n1+n2;
			n1 = n2;
			n2 = n3;
		}
		return n3;
	}
}	
