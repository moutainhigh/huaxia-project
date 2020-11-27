/**
 * Title: Student.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日上午10:51:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:Student2020年9月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日上午10:51:13
 */
public class Student {
	private String name;
	private String sex;
	private int age;
	/**
	 * Constructor
	 */
	public Student() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public Student(String name,String sex,int age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月21日上午10:51:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
