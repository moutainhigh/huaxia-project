/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日下午5:06:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Test2020年9月23日
 * @comments:
 * @param:匿名内部类的使用
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日下午5:06:21
 */
public class Test {
	/**
	 * @Title: test
	 *@Description: TODO
	 *@param worker
	 *@author: LiuWei
	 *@Date: 2020年9月23日下午5:07:07
	 */
	public void test(Worker worker){
		System.out.println(worker.getName()+"工作时间："+worker.workTime());
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
	 *@Date: 2020年9月23日下午5:06:21
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
