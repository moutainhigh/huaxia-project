/**
 * Title: StaticCodeBlock.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������5:19:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

/**
 * @class_name:StaticCodeBlock2020��9��11��
 * @comments: ���췽����;�̬������ִ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������5:19:56
 */
public class StaticCodeBlock {
	static{
		System.out.println("��̬�����ִ�С�����������");
	}
	/**
	 * Constructor
	 */
	public StaticCodeBlock() {
		// TODO Auto-generated constructor stub
		System.out.println("���췽��ִ��");
	}
	{
		System.out.println("���췽��ִ��.....");
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������5:19:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main������ʼִ��");
		System.out.println("������һ������");
		new StaticCodeBlock();
		System.out.println("�����ڶ�������");
		new StaticCodeBlock();
		System.out.println("��������������");
		new StaticCodeBlock();
	}
}
