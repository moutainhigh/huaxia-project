/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日上午10:53:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:Test2020年9月21日
 * @comments:面向对象的设计，类的设计，成员对象和成员方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日上午10:53:26
 */
public class Test {

	/**
	 * Constructor
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月21日上午10:53:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student = new Student("李默","男",23);
		System.out.println("姓名："+student.getName());
		System.out.println("性别："+student.getSex());
		System.out.println("年龄："+student.getAge());

	}

}
