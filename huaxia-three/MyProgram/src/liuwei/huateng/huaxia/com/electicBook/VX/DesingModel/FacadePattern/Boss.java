/**
 * Title: Boss.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������5:15:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacadePattern;

/**
 * @class_name:Boss2020��1��6��
 * @comments�����ģʽ��32�����ģʽ֮һ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������5:15:32
 */
public class Boss {

	/**
	 * 
	 */
	public Boss() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��6������5:15:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Secretary s = new Secretary();
		System.out.println("�ϰ��������Ҫ���Ϻ�ȥ����");
		s.trip("�Ϻ� ", 10);
		System.out.println("---------------------------------")	;
		System.out.println("�ϰ��������Ҫ��˸���ȥ�Է�");
		s.repast(8);
	}

}
