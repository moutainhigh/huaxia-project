/**
 * Title: Employee.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月31日上午10:06:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter6;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @class_name:Employee2019年12月31日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月31日上午10:06:13
 */
public class Employee2 implements Comparable<Employee2>,Cloneable {
	private String name;
	private double salary;
	private Date hireDay;
	/**
	 * 
	 */
	public Employee2(String name,double salary) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.salary = salary;
		hireDay = new Date();
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
	public int compareTo(Employee2 other) {
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
		return "Employee2 [name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}

	@Override
	protected Employee2 clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
	 Employee2 cloned = (Employee2)super.clone();
	 cloned.hireDay = (Date)hireDay.clone();
	 return cloned;
	}
	/**
	 * @Title: setHireDay
	 *@Description: TODO
	 *@param year
	 *@param month
	 *@param day
	 *@author: LiuWei
	 *@Date: 2019年12月31日上午10:56:44
	 */
	public void setHireDay(int year,int month,int day)
	{
		Date newHireDay = new GregorianCalendar(year,month-1,day).getTime();
		hireDay.setTime(newHireDay.getTime());
	}
}
