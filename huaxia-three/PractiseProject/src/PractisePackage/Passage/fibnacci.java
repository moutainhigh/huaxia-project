/**
 * Title: fibnacci.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������2:34:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:fibnacci2020��8��18��
 * @comments: ����ѭ���ķ������̣�����ڵݹ���˵
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������2:34:44
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
	 *@Date: 2020��8��18������2:34:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start,end;
		start = System.currentTimeMillis();
		calFibnacciByRecursive(40);
		end = System.currentTimeMillis();
		System.out.println("calFibnacciByRecursiveִ��ʱ��"+(end-start));
		start = System.currentTimeMillis();
		calFibnoacciByLoop(40);
		end = System.currentTimeMillis();
		System.out.println("calFibnoacciByLoopִ��ʱ��"+(end-start));
	}
	/**
	 * @Title: calFibnacciByRecursive
	 *@Description: TODO �ݹ��㷨
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��18������2:36:22
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
	 *@Description: TODO �����㷨
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��18������2:38:03
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
