/**
 * Title: TestTemplate.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������3:55:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestTemplate2020��10��12��
 * @comments: ģ�巽�����൱�ڽӿںͳ�����ͼ̳еķ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������3:55:35
 */
public class TestTemplate {

	/**
	 * Constructor
	 */
	public TestTemplate() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��10��12������3:55:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractTemplate template1 = new TakeMoney();
		template1.templateMethod();
		AbstractTemplate template3 = new SaveMoney();
		template3.templateMethod();
	}

}
