/**
 * Title: EqualsTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日上午11:06:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

import liuwei.huateng.huaxia.com.coreJava.chapter4.Employee;

/**
 * @class_name:EqualsTest2019年12月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日上午11:06:00
 */
public class EqualsTest {

	/**
	 * 
	 */
	public EqualsTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月30日上午11:06:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee3 alice1 = new Employee3("Alice Adams",75000.0,1987,12,15);
		Employee3 alice2 = alice1;
		Employee3 alice3 = new Employee3("Alice Adams",75000.0,1987,12,15);
		Employee3 bob = new Employee3("Bob brandson",50000,1989,10,1);
		System.out.println("alice1 == alice2:"+(alice1 == alice2));
		System.out.println("alice1 == alice3:"+(alice1 == alice3));
		System.out.println("alice1.equals(alice3):"+(alice1.equals(alice3)));
		System.out.println("alice1.equals(bob):"+(alice1.equals(bob)));
		System.out.println("bob.toString():"+bob);
		Manager2 carl = new Manager2("Carl Cracker",80000,1987,12,15);
		Manager2 boss = new Manager2("Carl Cracker",80000,1987,12,15);
		boss.setBonus(5000);
		System.out.println("boss.toString():"+boss);
		System.out.println("carl.equals(boss):"+carl.equals(boss));
		System.out.println("alice1.hashCode()"+alice1.hashCode());
		System.out.println("alice3.hashCode()"+alice3.hashCode());
		System.out.println("bob.hashCode()"+bob.hashCode());
		System.out.println("carl.hashCode()"+carl.hashCode());
	}

}
