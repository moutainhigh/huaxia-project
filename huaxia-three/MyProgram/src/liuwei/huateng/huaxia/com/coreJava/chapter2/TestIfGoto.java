/**
 * Title: TestIfGoto.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������10:25:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter2;

import java.util.Scanner;

/**
 * @class_name:TestIfGoto2019��12��26��
 * @comments: break label; ��������ǩ�������ĩβ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������10:25:24
 */
public class TestIfGoto {

	/**
	 * 
	 */
	public TestIfGoto() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��26������10:25:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		label:
		{
			System.out.println("������label����");
			Scanner sc = new Scanner(System.in);
			int condition = sc.nextInt();
			if(condition < 0) break label;
			System.out.println("label��佫Ҫ����");
		}
		System.out.println("������label���");
	}

}
