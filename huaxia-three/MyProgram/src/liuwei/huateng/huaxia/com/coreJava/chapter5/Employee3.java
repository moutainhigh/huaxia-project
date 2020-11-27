/**
 * Title: Employee3.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日上午10:50:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

import java.util.Date;

/**
 * @class_name:Employee32019年12月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日上午10:50:27
 */
public class   Employee3{
	private String name;
	private double salary;
	private Date hireDay;
	/**
	 * 
	 */
	public Employee3(String name,double salary,int year,int month,int day) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.salary = salary;
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
	@Override
	public String toString() {
		return "Employee3 [name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hireDay == null) ? 0 : hireDay.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee3 other = (Employee3) obj;
		if (hireDay == null) {
			if (other.hireDay != null)
				return false;
		} else if (!hireDay.equals(other.hireDay))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		return true;
	}
	
}
