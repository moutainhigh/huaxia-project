/**
 * Title: Test13.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������3:23:10
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

import java.util.Scanner;

/**
 * @class_name:Test132020��8��18��
 * @comments:����һ���ַ����ֱ�ͳ������Ӣ����ĸ���ո����ֺ������ַ��ĸ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������3:23:10
 */
public class Test13 {
	
	/**
	 * Constructor
	 */
	public Test13() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������3:23:10
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int abccount = 0;
		int spacecount = 0;
		int numcount = 0;
		int othercount = 0;
		Scanner input = new Scanner(System.in);
		System.out.println("������һ���ַ�");
		String toString = input.nextLine();
		char[] ch = toString.toCharArray();
		for(int i=0;i<ch.length;i++){
			if(Character.isLetter(ch[i])){
				abccount++;
			}else if(Character.isDigit(ch[i])){
				numcount++;
			}else if(Character.isSpaceChar(ch[i])){
				spacecount++;
			}else{
				othercount++;
			}
		}
		System.out.println(abccount);
		System.out.println(spacecount);
		System.out.println(numcount);
		System.out.println(othercount);
	}

}
