/**
 * Title: PersonTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日上午10:03:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

/**
 * @class_name:PersonTest2019年12月30日
 * @comments:测试继承的多态性
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日上午10:03:09
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
	 *@Date: 2019年12月30日上午10:03:09
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
