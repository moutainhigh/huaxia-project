/**
 * Title: EmployeeTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月26日下午5:23:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;

/**
 * @class_name:EmployeeTest2019年12月26日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月26日下午5:23:02
 */
public class EmployeeTest {

	/**
	 * 
	 */
	public EmployeeTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月26日下午5:23:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//fill the staff array with three Employee objects
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Carl Cracker",75000,1987,12,15);
		staff[1] = new Employee("Harry Hacker",5000,1989,10,1);
		staff[2] = new Employee("Tony Tester",40000,1990,3,15);
		//raise everyonts salary by 5%
		for(Employee e:staff)
			e.raiseSalary(5);
		//print out information about all Employee object 
		for(Employee e:staff)
		{
			System.out.println(e.toString());
		}
	}

}
