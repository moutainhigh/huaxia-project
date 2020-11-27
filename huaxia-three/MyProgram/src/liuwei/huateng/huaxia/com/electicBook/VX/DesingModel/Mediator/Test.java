/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午10:02:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

/**
 * @class_name:Test2020年1月8日
 * @comments:测试中介者欧式
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午10:02:37
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午10:02:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarriageAgency agency = new MarriageAgencyImpl();
		Person m1 =new Man("John",20,Sex.MALE,18,agency);
		Person m2 =new Man("Mike",27,Sex.MALE,25,agency);
		Person w1 =new Woman("Mary",25,Sex.FEMALE,27,agency);
		Person w2 =new Woman("Jane",20,Sex.FEMALE,22,agency);
		m1.findPartner();
		m2.findPartner();
	}

}
