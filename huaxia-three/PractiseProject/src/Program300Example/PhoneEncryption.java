/**
 * Title: PhoneEncryption.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������9:50:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class_name:PhoneEncryption2020��9��22��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������9:50:18
 */
public class PhoneEncryption {

	/**
	 * Constructor
	 */
	public PhoneEncryption() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 * @throws IOException 
	 *@Date: 2020��9��22������9:50:18
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("��������Ҫ���ܵĵ绰���룺");
		BufferedReader stdin= new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		char[] a = s.toCharArray();
		int[] b = new int[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = ((a[i] -'0')+5)%10;
		}
		System.out.println("���ܺ�������ǣ�");
		for(int i = b.length-1;i>=0;i--){
			System.out.print(b[i]);
		}
		System.out.println();
	}

}
