/**
 * Title: RandomPasswordEightChar.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午2:19:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

import java.util.Random;

/**
 * @class_name:RandomPasswordEightChar2020年8月6日
 * @comments:生成8位随机数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午2:19:47
 */
public class RandomPasswordEightChar {
	private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";
	/**
	 * Constructor
	 */
	public RandomPasswordEightChar() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午2:19:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			System.out.println(randomPassword());
		}
	}
	/**
	 * @Title: nextChar
	 *@Description: TODO
	 *@param rnd 随机字母
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午2:30:01
	 */
	private static char nextChar(Random rnd){
		switch(rnd.nextInt(4)){
		case 0:
			return (char)('a'+rnd.nextInt(26));
		case 1:
			return (char)('A'+rnd.nextInt(26));
		case 2:
			return (char)('0'+rnd.nextInt(10));
		default:
			return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
		}
	}
	/**
	 * @Title: randomPassword
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午2:30:10
	 */
	public static String randomPassword(){
		char[] chars = new char[8];
		Random rnd = new Random();
		for(int i=0;i<8;i++){
			chars[i] = nextChar(rnd);
		}
		return new String(chars);
	}
}
