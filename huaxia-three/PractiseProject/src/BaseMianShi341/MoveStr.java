/**
 * Title: MoveStr.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������1:41:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

import java.util.Scanner;

/**
 * @class_name:MoveStr2020��9��22��
 * @comments:
 * @param�ַ����ģ���*���ƶ���ǰ�������Ҳ��ı�ԭ���ַ�����˳��
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������1:41:31
 */
public class MoveStr {

	/**
	 * Constructor
	 */
	public MoveStr() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��22������1:41:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�������ַ�����");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		char[] ch = str.toCharArray();
		System.out.println("����ǰ���ַ���Ϊ��"+str);
		int sum = beginMove(ch);
		System.out.println("�ַ����С�*��������Ϊ��"+sum);
	}
	/**
	 * @Title: beginMove
	 *@Description: TODO
	 *@param ch
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��22������1:46:33
	 */
	public static int beginMove(char[] ch){
		int i,j= ch.length -1;
		for(i =j;j>=0;j--){
			if(ch[i] != '*'){
				i--;
			}else if(ch[j] != '*'){
				ch[i] = ch[j];
				ch[j] = '*';
				i--;
			}
		}
		System.out.println("������ַ���Ϊ��");
		for(int k =0;k<ch.length;k++){
			System.out.print(ch[k]);
		}
		System.out.println();
		return i+1;
	}
}
