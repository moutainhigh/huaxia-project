/**
 * Title: StaticTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��27������9:46:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter4;

/**
 * @class_name:StaticTest2019��12��27��
 * @comments: ����static����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��27������9:46:28
 */
public class StaticTest {

	/**
	 * 
	 */
	public StaticTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��27������9:46:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee2[] staff = new Employee2[3];
		staff[0] = new Employee2("Tom",40000);
		staff[1] = new Employee2("Dick",60000);
		staff[2] = new Employee2("Harry",65000);
		//print out informaion about all Employee objects
		for(Employee2 e :staff)
		{
			e.setId();
			System.out.println(e.toString());
		}
		int n= Employee2.getNextId();
		System.out.println("Next available id="+n);
	}	

}
