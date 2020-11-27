/**
 * Title: ParamTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月27日上午10:15:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;

/**
 * @class_name:ParamTest2019年12月27日
 * @comments:交换的值引用和对象的引用是有所不同的。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月27日上午10:15:51
 */
public class ParamTest {

	/**
	 * 
	 */
	public ParamTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月27日上午10:15:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Test1:Methods can not modify numeric parameters
		 */
		System.out.println("Testing tripleValue:");
		double percent = 10;
		System.out.println("before:percent="+percent);
		tripleValue(percent);
		System.out.println("After:percent="+percent);
		
		/*
		 * Test2:Methods can change the state of objecdt parameters
		 */
		System.out.println("\nTest tripleSalary");
		Employee3 harry = new Employee3("Harry",50000);
		System.out.println("Before:salary="+harry.getSalary());
		tripleSalary(harry);
		System.out.println("After:salary="+harry.getSalary());
		
		/*
		 * Test3:Methods can not attach new objects to object parameters
		 */
		System.out.println("\nTesting swap:");
		Employee3 a = new Employee3("Alice",70000);
		Employee3 b = new Employee3("Bob",60000);
		System.out.println("Before a ="+a.getName());
		System.out.println("Before b ="+b.getName());
		swap(a,b);
		System.out.println("after a ="+a.getName());
		System.out.println("after b ="+b.getName());
	}
	/**
	 * @Title: tripleValue
	 *@Description: TODO
	 *@param x
	 *@author: LiuWei
	 *@Date: 2019年12月27日上午10:17:05
	 */
	public static void tripleValue(double x)//doest not work
	{
		x = 3*x;
		System.out.println("End of method: x="+x);
	}
	/**
	 * @Title: tripleSalary
	 *@Description: TODO
	 *@param x
	 *@author: LiuWei
	 *@Date: 2019年12月27日上午10:22:08
	 */
	public static void tripleSalary(Employee3 x){
		x.raiseSalary(200.0);
		System.out.println("End of method:salary="+x.getSalary());
	}
	/**
	 * @Title: swap
	 *@Description: TODO
	 *@param x
	 *@param y
	 *@author: LiuWei
	 *@Date: 2019年12月27日上午10:23:49
	 */
	public static void swap(Employee3 x,Employee3 y)
	{
		Employee3 temp = x;
		x = y;
		y = temp;
		System.out.println("End of method:x="+x.getName());
		System.out.println("End of method:y="+y.getName());
	}
}	
/**
 * @class_name:Employee32019年12月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月27日上午10:20:07
 */
class Employee3
{
	private String name;
	private double salary;
	public Employee3(String n,double s)
	{
		name = n;
		salary = s;
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
	/**
	 * @Title: raiseSalary
	 *@Description: TODO
	 *@param byPercent
	 *@author: LiuWei
	 *@Date: 2019年12月27日上午10:19:52
	 */
	public void raiseSalary(Double byPercent)
	{
		double raise = salary * byPercent/100;
		salary+=raise;
	}
}