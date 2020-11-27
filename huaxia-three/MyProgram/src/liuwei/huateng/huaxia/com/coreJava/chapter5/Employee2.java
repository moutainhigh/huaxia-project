/**
 * Title: Employee2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��30������9:55:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

import java.util.Date;

/**
 * @class_name:Employee22019��12��30��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��30������9:55:32
 */
public class Employee2 extends Person{
	private double salary;
	private Date hireDay;
	/**
	 * 
	 */
	public Employee2(String name,double salary,int year,int month,int day) {
		// TODO Auto-generated constructor stub
		super(name);
		this.salary = salary;
		hireDay = new Date(year,month,day);
	}
	@Override
	public String getDescripton() {
		// TODO Auto-generated method stub
		return String.format("an employee with a salary of $%.2f", salary);
	}
	public double getSalary() {
		return salary;
	}
	public Date getHireDay() {
		return hireDay;
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
	 *@Date: 2019��12��30������9:59:09
	 */
	public void raiseSalary(double byPercent){
		double raise = salary * byPercent/100;
		raise += raise;
	}
}
