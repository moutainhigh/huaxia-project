/**
 * Title: Employee.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日上午10:06:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter6;

/**
 * @class_name:Employee2019年12月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日上午10:06:13
 */
public class Employee implements Comparable<Employee> {
	private String name;
	private double salary;
	
	/**
	 * 
	 */
	public Employee(String name,double salary) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.salary = salary;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月31日上午10:06:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public int compareTo(Employee other) {
		// TODO Auto-generated method stub
		return Double.compare(salary, other.salary);
	}
	/**
	 * @Title: raiseSalary
	 *@Description: TODO
	 *@param byPercent
	 *@author: LiuWei
	 *@Date: 2019年12月31日上午10:10:41
	 */
	public void raiseSalary(double byPercent)
	{
		double raise = salary *byPercent/100;
		salary += raise;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}
	
}
