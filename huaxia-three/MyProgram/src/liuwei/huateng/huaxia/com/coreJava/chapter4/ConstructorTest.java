/**
 * Title: ConstructorTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月27日上午10:54:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;

import java.util.Random;

/**
 * @class_name:ConstructorTest2019年12月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月27日上午10:54:46
 */
public class ConstructorTest {

	/**
	 * 
	 */
	public ConstructorTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月27日上午10:54:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//fill the staff array with three Employee Objects
		Employee4[] staff = new Employee4[3];
		staff[0] = new Employee4("Harry",40000);
		staff[1] = new Employee4(60000);
		staff[2] = new Employee4();
		//print out information about all Employee objects
		for(Employee4 e:staff){
			System.out.println(e.toString());
		}
	}

}
class Employee4
{
	private static int nextId;
	
	private int id;
	private String name= "";//instance field initialization
	private double salary;
	//static initialization block
	static
	{
		Random generator = new Random();
		//set nextId to a random number between 0 and 9999
		nextId = generator.nextInt(10000);
	}
	//object initialization block
	{
		id = nextId;
		nextId++;
	}
	//three overloaded constructors
	public Employee4(String n,double s)
	{
		name = n;
		salary = s;
	}
	public Employee4(double s)
	{
		//calls the Employee(String ,double) constructor
		this("Employee #"+nextId,s);
	}
	//the default constructor
	public Employee4()
	{
		//nameinitialazed to ""--see above
		//salary not explicitly set -- initialized to 0
		//id initialized in initialization block
		this.name ="";
		this.salary = 0;
	}
	public static int getNextId() {
		return nextId;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
	public static void setNextId(int nextId) {
		Employee4.nextId = nextId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee4 [id=" + id + ", name=" + name + ", salary=" + salary + ", nextId=" + nextId +"]";
	}
}