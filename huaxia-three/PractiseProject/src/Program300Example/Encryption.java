/**
 * Title: Encryption.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午3:15:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:Encryption2020年9月18日
 * @comments:异或运算符，两位相异，为1，异或运算
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午3:15:16
 */
public class Encryption {

	/**
	 * Constructor
	 */
	public Encryption() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午3:15:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入一个英文字符串或解密字符串");
		String password = scan.nextLine();
		char[] array = password.toCharArray();
		for(int i=0;i<array.length;i++){
			array[i] = (char)(array[i]^20000);
		}
		System.out.println("加密或者解密结果如下：");
		System.err.println(new String(array));
	}

}
