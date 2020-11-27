/**
 * Title: PackageTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月27日上午11:21:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;
import static java.lang.System.*;
/**
 * @class_name:PackageTest2019年12月27日
 * @comments: 导入包，导入静态方法和静态类
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月27日上午11:21:54
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
	 *@Date: 2019年12月27日上午11:21:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee harry = new Employee("Harry Hacker",5000,1989,19,1);
		harry.raiseSalary(5);
		out.println(harry.toString());
	}
}
