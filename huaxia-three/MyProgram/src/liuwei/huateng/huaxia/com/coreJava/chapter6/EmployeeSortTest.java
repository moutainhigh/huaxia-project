/**
 * Title: EmployeeSortTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��31������10:11:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter6;

import java.util.Arrays;

/**
 * @class_name:EmployeeSortTest2019��12��31��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��31������10:11:53
 */
public class EmployeeSortTest {

	/**
	 * 
	 */
	public EmployeeSortTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��31������10:11:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Harry Hacker",35000);
		staff[1] = new Employee("Carl cracker",75000);
		staff[2] = new Employee("Tony Test",38000);
		Arrays.sort(staff);  
		for(Employee e:staff)
			System.out.println(e.toString());
	}

}
