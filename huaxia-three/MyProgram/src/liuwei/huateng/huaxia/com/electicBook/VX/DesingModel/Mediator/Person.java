/**
 * Title: Person.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午9:45:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

/**
 * @class_name:Person2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午9:45:32
 */
public abstract class Person {
	String name;//姓名
	int age;//年龄
	Sex sex;
	int requestAge;//要求对象的年龄，对对象只有这一个要求
	MarriageAgency agency;//婚姻中介
	/**
	 * 
	 */
	public Person(String name,int age,Sex sex,int requestAge,MarriageAgency agency	) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.requestAge = requestAge;
		this.agency = agency;
		agency.register(this);
	}
	/**
	 * @Title: findPartner
	 *@Description: TODO 找寻对象
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午9:52:36
	 */
	public void findPartner(){
		agency.pair(this);;
	}
}
enum Sex{
	MALE,FEMALE;
}
