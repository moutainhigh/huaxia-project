/**
 * Title: Employee2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月27日上午9:41:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;

/**
 * @class_name:Employee22019年12月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月27日上午9:41:01
 */
public class Employee2 {
	private static int nextId = 1;
	private String name;
	private double salary;
	private int id;
	/**
	 * 
	 */
	public Employee2(String n,double s) {
		// TODO Auto-generated constructor stub
		name = n;
		salary = s;
		id =0;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月27日上午9:41:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee2 e = new Employee2("Harry",50000);
		Employee2 e2 = new Employee2("Harry",50000);
		System.out.println(e.toString());
		System.out.println(e2.toString());
	}

	public static int getNextId() {
		return nextId;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public int getId() {
		return id;
	}

	public static void setNextId(int nextId) {
		Employee2.nextId = nextId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setId() {
		this.id = nextId;
		nextId++;
	}

	@Override
	public String toString() {
		return "Employee2 [name=" + name + ", salary=" + salary + ", id=" + id +", nextId=" + nextId + "]";
	}
	
}
