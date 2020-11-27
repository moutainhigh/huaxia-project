/**
 * Title: CaesarPassword.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������10:13:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:CaesarPassword2020��9��18��
 * @comments:��������ļ��ܺͽ��ܵ�ʵ��
 * @param:���ǰ���ĸ������ƶ���λ��
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������10:13:14
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
	 *@Date: 2020��9��18������10:13:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[A ����][J ����],please choose one");
		Scanner c =new Scanner(System.in);
		String s1= c.nextLine();
		if("A".equalsIgnoreCase(s1)){
			System.out.println("����������");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			System.out.println("��������Կ");
			Scanner sc1 = new Scanner(System.in);
			int key = sc1.nextInt();
			Encryption(s,key);
		}else 	if("J".equalsIgnoreCase(s1)){
			System.out.println("����������");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			System.out.println("��������Կ");
			Scanner sc1 = new Scanner(System.in);
			int key = sc1.nextInt();
			Decrypt(s,key);
		}
	}
	/**
	 * @Title: Encryption
	 *@Description: TODO
	 *@param str ����
	 *@param k
	 *@author: LiuWei
	 *@Date: 2020��9��18������10:14:59
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
		System.out.println(str +" ���ܺ�Ϊ��"+string);
	}
	/**
	 * @Title: Decrypt
	 *@Description: TODO
	 *@param str  ���ܷ���
	 *@param n
	 *@author: LiuWei
	 *@Date: 2020��9��18������10:20:01
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
		System.out.println(str +" ���ܺ�Ϊ��"+string);
	}
}
