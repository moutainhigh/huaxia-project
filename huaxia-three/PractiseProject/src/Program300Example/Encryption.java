/**
 * Title: Encryption.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������3:15:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:Encryption2020��9��18��
 * @comments:������������λ���죬Ϊ1���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������3:15:16
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
	 *@Date: 2020��9��18������3:15:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("������һ��Ӣ���ַ���������ַ���");
		String password = scan.nextLine();
		char[] array = password.toCharArray();
		for(int i=0;i<array.length;i++){
			array[i] = (char)(array[i]^20000);
		}
		System.out.println("���ܻ��߽��ܽ�����£�");
		System.err.println(new String(array));
	}

}
