/**
 * Title: Manager2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日上午10:59:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:Manager22019年12月30日
 * @comments:liuwei.huateng.huaxia.com.coreJava.chapter5.Manager2
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日上午10:59:52
 */
public class Manager2 extends Employee3 {
	private double bonus;
	/**
	 * @param name
	 * @param salary
	 * @param year
	 * @param month
	 * @param day
	 */
	public Manager2(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		// TODO Auto-generated constructor stub
	 bonus =0;
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
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月30日上午10:59:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(bonus);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager2 other = (Manager2) obj;
		if (Double.doubleToLongBits(bonus) != Double.doubleToLongBits(other.bonus))
			return false;
		return true;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

}
