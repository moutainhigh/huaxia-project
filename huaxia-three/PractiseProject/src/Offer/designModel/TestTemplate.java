/**
 * Title: TestTemplate.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午3:55:35
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestTemplate2020年10月12日
 * @comments: 模板方法，相当于接口和抽象类和继承的方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午3:55:35
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
	 *@Date: 2020年10月12日下午3:55:35
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractTemplate template1 = new TakeMoney();
		template1.templateMethod();
		AbstractTemplate template3 = new SaveMoney();
		template3.templateMethod();
	}

}
