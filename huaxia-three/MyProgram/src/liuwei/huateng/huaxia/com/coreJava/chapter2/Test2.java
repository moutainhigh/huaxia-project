/**
 * Title: Test2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月25日下午4:19:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

/**
 * @class_name:Test22019年12月25日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月25日下午4:19:33
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
	 *@Date: 2019年12月25日下午4:19:33
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
	 *@Date: 2019年12月25日下午4:20:47
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
	 *@Date: 2019年12月25日下午4:21:20
	 */
	public static  void add2(){
		int a = 1;
		int b = 2;
		int c = 3;
		int temp = a+=b;
		System.out.println(temp+=c);
	}
}
