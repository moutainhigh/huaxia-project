/**
 * Title: JudgeSeason.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������3:27:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:JudgeSeason2020��9��18��
 * @comments:switch case����ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������3:27:44
 */
public class JudgeSeason {

	/**
	 * Constructor
	 */
	public JudgeSeason() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������3:27:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("������һ���·ݣ����������֣��������������������ĸ����ڣ�");
		int month = scan.nextInt();
		switch(month){
		case 12:
		case 1:
		case 2:
			System.out.println("��������·����ڶ�����");
			break;
		case 3:
		case 4:
		case 5:
			System.out.println("��������·����ڴ�����");
			break;
		case 6:
		case 7:
		case 8:
			System.out.println("��������·������ļ���");
			break;
		case 9:
		case 10:
		case 11:
			System.out.println("��������·������＾��");
			break;
		}
	}

}
