/**
 * Title: PersonTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��30������10:03:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:PersonTest2019��12��30��
 * @comments:���Լ̳еĶ�̬��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��30������10:03:09
 */
public class PersonTest {

	/**
	 * 
	 */
	public PersonTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��30������10:03:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person[] people = new Person[2];
		//fill the people array with Student and Employee objects
		people[0] = new Employee2("Harry Hacker",50000,1989,10,1);
		people[1] = new Student("Maria Morris","computer science");
		//print out names and descriptions of all Person object
		for(Person p:people)
			System.out.println(p.getName()+","+p.getDescripton());
	}

}
