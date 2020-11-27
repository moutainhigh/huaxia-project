/**
 * Title: Employee.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��27������4:43:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

import java.util.Date;

/**
 * @class_name:Employee2019��12��27��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��27������4:43:49
 */
public class Employee {	
	private String name;
	private double salary;
	private Date hireDay;
	/**
	 * 
	 */
	public Employee(String name,double salary,int year,int month,int day) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.salary = salary;
		hireDay = new Date(year,month,day);
	}
	/**
	 * 
	 */
	public Employee(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.salary = salary;
		Date date = new Date();
		hireDay = new Date(date.getYear(),date.getMonth(),date.getDay());
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��27������4:43:49
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
	 *@Date: 2019��12��27������4:48:41
	 */
	public void raiseSalary(double byPercent){
		double raise = salary*byPercent/100;
		salary+=raise;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
	}
	
}
