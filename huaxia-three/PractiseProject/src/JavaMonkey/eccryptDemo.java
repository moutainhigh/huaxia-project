/**
 * Title: eccryptDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月14日下午2:47:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:eccryptDemo2020年9月14日
 * @commen0ts:加密算法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月14日下午2:47:58
 */
public class eccryptDemo {

	/**
	 * Constructor
	 */
	public eccryptDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月14日下午2:47:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(encrypt(3956));
	}
	/***
	 * @Title: encrypt
	 *@Description: TODO
	 *@param data
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月14日下午2:50:03
	 */
	public static int encrypt(int data){
		int data1 = data %10;
		int data2 = data/10%10;
		int data3 = data/100%10;
		int data4 = data/1000;
		System.out.println(data4+" "+data3+" "+data2+" "+data1);
		data1 = (data1+5)%10;
		data2 = (data2+5)%10;
		data3 = (data3+5)%10;
		data4 = (data4+5)%10;
		int result = data1*1000+data2*100+data3*10+data4;
		return result;
	}
}
