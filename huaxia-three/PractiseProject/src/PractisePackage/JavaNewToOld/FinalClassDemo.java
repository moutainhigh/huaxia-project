/**
 * Title: FinalClassDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��6������5:05:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.JavaNewToOld;

/**
 * @class_name:FinalClassDemo2020��8��6��
 * @comments:����һ��final�࣬�����ܱ��̳У����Բ�����������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������5:05:31
 */
public class FinalClassDemo {
	private String message = "����һ��final��";
	private String enable = "�����ܱ��̳У����Բ�����������";
	
	/**
	 * Constructor
	 */
	public FinalClassDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��6������5:05:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FinalClassDemo demo = new FinalClassDemo();//�������ʵ��
		System.out.println(demo.message);
		System.out.println(demo.enable);
	}

}
