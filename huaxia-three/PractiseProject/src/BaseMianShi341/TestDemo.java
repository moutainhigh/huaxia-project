/**
 * Title: TestDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午11:01:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:TestDemo2020年9月23日
 * @comments:Safe1
Safe3
Safe4
没有异常，catch语句不会执行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午11:01:48
 */
public class TestDemo {

	/**
	 * Constructor
	 */
	public TestDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午11:01:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestDemo().demo();
	}
	/**
	 * @Title: demo
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午11:07:26
	 */
	public void demo(){
		try{
			test();
			System.out.println("Safe1");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Safe2");
		}finally{
			System.out.println("Safe3");
		}
		System.out.println("Safe4");
	}
	public void test(){
	}
}
