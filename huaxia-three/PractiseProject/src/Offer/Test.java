/**
 * Title: Test.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������5:06:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Test2020��9��23��
 * @comments:
 * @param:�����ڲ����ʹ��
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������5:06:21
 */
public class Test {
	/**
	 * @Title: test
	 *@Description: TODO
	 *@param worker
	 *@author: LiuWei
	 *@Date: 2020��9��23������5:07:07
	 */
	public void test(Worker worker){
		System.out.println(worker.getName()+"����ʱ�䣺"+worker.workTime());
	}
	/**
	 * Constructor
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��23������5:06:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		test.test(new Worker(){

			@Override
			public int workTime() {
				// TODO Auto-generated method stub
				return 8;
			}
			public String getName(){
				return "alex";
			}
		});
	}
}
