/**
 * Title: Employee.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��26������5:15:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;

import java.util.Date;

/**
 * @class_name:Employee2019��12��26��
 * @comments: ����������Ʊ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��26������5:15:04
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
	 *@Date: 2019��12��26������5:22:16
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
