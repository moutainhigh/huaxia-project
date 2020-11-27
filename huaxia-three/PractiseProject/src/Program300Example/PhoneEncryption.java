/**
 * Title: PhoneEncryption.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日上午9:50:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class_name:PhoneEncryption2020年9月22日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日上午9:50:18
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
	 *@Date: 2020年9月22日上午9:50:18
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("请输入需要加密的电话号码：");
		BufferedReader stdin= new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		char[] a = s.toCharArray();
		int[] b = new int[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = ((a[i] -'0')+5)%10;
		}
		System.out.println("加密后的数字是：");
		for(int i = b.length-1;i>=0;i--){
			System.out.print(b[i]);
		}
		System.out.println();
	}

}
