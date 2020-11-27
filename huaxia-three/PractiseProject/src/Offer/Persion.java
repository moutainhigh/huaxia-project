/**
 * Title: Persion.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日下午3:07:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Persion2020年9月23日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日下午3:07:07
 */
public class Persion {
	private String name;
	private String age;
	private String sex;
	private String weight;
	/**
	 * Constructor
	 */
	public Persion() {
		// TODO Auto-generated constructor stub
	}
	public Persion(String name,String age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日下午3:07:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void testMethod(){
		
	}
	public void testMethod2(){
		
	}

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public String getWeight() {
		return weight;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Persion [name=" + name + ", age=" + age + ", sex=" + sex + ", weight=" + weight + "]";
	}
	
}
