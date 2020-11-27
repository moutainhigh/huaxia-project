/**
 * Title: Employee.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月26日下午5:15:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;

import java.util.Date;

/**
 * @class_name:Employee2019年12月26日
 * @comments: 面向帝乡的设计编程
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月26日下午5:15:04
 */
public class Employee {
	private String name;
	private double salary;
	private Date hireDay;
	/**
	 * 
	 */
	public Employee(String n,double s,int year,int month,int day) {
		// TODO Auto-generated constructor stub
		name = n;
		salary = s;
		hireDay = new Date(year,month,day);
	}
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
	public Date getHireDay() {
		return hireDay;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	/**
	 * @Title: raiseSalary
	 *@Description: TODO
	 *@param byPercent
	 *@author: LiuWei
	 *@Date: 2019年12月26日下午5:22:16
	 */
	public void raiseSalary(double byPercent){
		double raise = salary * byPercent/100;
		salary += raise;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
	}
	
}
