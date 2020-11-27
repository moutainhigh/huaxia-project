/**
 * Title: CaesarPassword.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日上午10:13:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:CaesarPassword2020年9月18日
 * @comments:凯撒密码的加密和解密的实现
 * @param:就是把字母像后面移动几位。
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日上午10:13:14
 */
public class CaesarPassword {

	/**
	 * Constructor
	 */
	public CaesarPassword() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午10:13:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[A 加密][J 解密],please choose one");
		Scanner c =new Scanner(System.in);
		String s1= c.nextLine();
		if("A".equalsIgnoreCase(s1)){
			System.out.println("请输入明文");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			System.out.println("请输入密钥");
			Scanner sc1 = new Scanner(System.in);
			int key = sc1.nextInt();
			Encryption(s,key);
		}else 	if("J".equalsIgnoreCase(s1)){
			System.out.println("请输入密文");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			System.out.println("请输入密钥");
			Scanner sc1 = new Scanner(System.in);
			int key = sc1.nextInt();
			Decrypt(s,key);
		}
	}
	/**
	 * @Title: Encryption
	 *@Description: TODO
	 *@param str 加密
	 *@param k
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午10:14:59
	 */
	public static void Encryption(String str,int k){
		String string = "";
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			if(c >= 'a' && c <= 'z'){
				c+=k %26;
				if(c < 'a') 
					c+= 26;
				if(c >'z')
					c -= 26;
			}else if(c >= 'A' && c <= 'Z'){
				c+=k %26;
				if(c < 'A') 
					c+= 26;
				if(c >'Z')
					c -= 26;
			}
			string += c;
		}
		System.out.println(str +" 加密后为："+string);
	}
	/**
	 * @Title: Decrypt
	 *@Description: TODO
	 *@param str  解密方法
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020年9月18日上午10:20:01
	 */
	public static void Decrypt(String str,int n){
		int k = Integer.parseInt("-" +n);
		String string = "";
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			if(c >= 'a' && c <= 'z'){
				c+=k %26;
				if(c < 'a') 
					c+= 26;
				if(c >'z')
					c -= 26;
			}else if(c >= 'A' && c <= 'Z'){
				c+=k %26;
				if(c < 'A') 
					c+= 26;
				if(c >'Z')
					c -= 26;
			}
			string += c;
		}
		System.out.println(str +" 解密后为："+string);
	}
}
