/**
 * Title: RandomPassword.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��6������2:12:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

import java.util.Random;

/**
 * @class_name:RandomPassword2020��8��6��
 * @comments:�����λ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������2:12:29
 */
public class RandomPassword {

	/**
	 * Constructor
	 */
	public RandomPassword() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��6������2:12:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			System.out.println(randomPassword());
		}
	}
	public static String randomPassword(){
		char[] chars = new char[6];
		Random rnd = new Random();
		for(int i=0;i<6;i++){
			chars[i] = (char)('0'+rnd.nextInt(10));
		}
		return new String(chars);
	}
}
