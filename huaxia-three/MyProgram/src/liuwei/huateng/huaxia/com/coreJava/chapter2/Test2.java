/**
 * Title: Test2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��25������4:19:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:Test22019��12��25��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��25������4:19:33
 */
public class Test2 {

	/**
	 * 
	 */
	public Test2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��25������4:19:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		add1();
		add2();
	}
	/**
	 * @Title: add1
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��25������4:20:47
	 */
	public static  void add1(){
		int a = 1;
		int b = 2;
		int c = 3;
		System.out.println(a+=b+=c);
	}
	/**
	 * @Title: add2
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��25������4:21:20
	 */
	public static  void add2(){
		int a = 1;
		int b = 2;
		int c = 3;
		int temp = a+=b;
		System.out.println(temp+=c);
	}
}
