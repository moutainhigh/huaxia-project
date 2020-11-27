/**
 * Title: ArrayListTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月30日下午2:39:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * @class_name:ArrayListTest2019年12月30日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月30日下午2:39:07
 */
public class ArrayListTest {

	/**
	 * 
	 */
	public ArrayListTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月30日下午2:39:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//fill the staff array list with three Employee objects
		ArrayList<Employee> staff =new ArrayList<>();
		staff.add(new Employee("carl Cracker",75000,1987,12,15));
		staff.add(new Employee("Harry Hacker",5000,1987,12,15));
		staff.add(new Employee("Tony Test",40000,1987,12,15));
		//raise everyone salary by 5%
		for(Employee e :staff)
		{
			e.raiseSalary(5);
		}
		//print out information about all Employee objects
		for(Employee e:staff)
			System.out.println(e.toString());
	}

}
