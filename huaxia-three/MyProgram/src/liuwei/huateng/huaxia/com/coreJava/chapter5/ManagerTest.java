/**
 * Title: ManagerTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月27日下午4:52:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:ManagerTest2019年12月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月27日下午4:52:31
 */
public class ManagerTest {

	/**
	 * 
	 */
	public ManagerTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月27日下午4:52:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager boss = new Manager("Carl Cracker",80000,1987,12,15);
		boss.setBonus(5000);
		Employee[] staff = new Employee[3];
		staff[0] = boss;
		staff[1] = new Employee("Harry Hacker",50000,1989,10,1);
		staff[2] = new Employee("Tommy Tester",40000,1990,3,15);
		for(Employee e:staff)
		{
			System.out.println(e.toString());
		}
	}

}
