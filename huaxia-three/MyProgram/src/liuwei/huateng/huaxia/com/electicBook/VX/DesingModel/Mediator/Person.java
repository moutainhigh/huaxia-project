/**
 * Title: Person.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������9:45:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

/**
 * @class_name:Person2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������9:45:32
 */
public abstract class Person {
	String name;//����
	int age;//����
	Sex sex;
	int requestAge;//Ҫ���������䣬�Զ���ֻ����һ��Ҫ��
	MarriageAgency agency;//�����н�
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
	 *@Description: TODO ��Ѱ����
	 *@author: LiuWei
	 *@Date: 2020��1��8������9:52:36
	 */
	public void findPartner(){
		agency.pair(this);;
	}
}
enum Sex{
	MALE,FEMALE;
}
