/**
 * Title: PackageTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��27������11:21:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;
import static java.lang.System.*;
/**
 * @class_name:PackageTest2019��12��27��
 * @comments: ����������뾲̬�����;�̬��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��27������11:21:54
 */
public class PackageTest {

	/**
	 * 
	 */
	public PackageTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param ar++gs
	 *@author: LiuWei
	 *@Date: 2019��12��27������11:21:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee harry = new Employee("Harry Hacker",5000,1989,19,1);
		harry.raiseSalary(5);
		out.println(harry.toString());
	}
}
