/**
 * Title: StaticTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月27日上午9:46:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;

/**
 * @class_name:StaticTest2019年12月27日
 * @comments: 测试static方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月27日上午9:46:28
 */
public class StaticTest {

	/**
	 * 
	 */
	public StaticTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月27日上午9:46:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee2[] staff = new Employee2[3];
		staff[0] = new Employee2("Tom",40000);
		staff[1] = new Employee2("Dick",60000);
		staff[2] = new Employee2("Harry",65000);
		//print out informaion about all Employee objects
		for(Employee2 e :staff)
		{
			e.setId();
			System.out.println(e.toString());
		}
		int n= Employee2.getNextId();
		System.out.println("Next available id="+n);
	}	

}
