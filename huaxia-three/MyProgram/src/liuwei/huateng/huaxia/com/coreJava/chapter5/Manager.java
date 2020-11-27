/**
 * Title: Manager.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月27日下午4:50:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:Manager2019年12月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月27日下午4:50:02
 */
public class Manager extends Employee {
	private double bonus;
	/**
	 * @param name
	 * @param salary
	 * @param year
	 * @param month
	 * @param day
	 */
	public Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		// TODO Auto-generated constructor stub
		bonus =0;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月27日下午4:50:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	/**
	 * 
	 */
	public double getSalary(){
		double baseSalary = super.getSalary();
		return baseSalary+bonus;
	}

	@Override
	public String toString() {
		return "Manager [name=" + super.getName() + ", salary=" + getSalary() + ", hireDay=" + super.getHireDay() + " [bonus=" + bonus +"]";
	}
	
}
