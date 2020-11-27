/**
 * Title: ConTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��31������10:48:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter6;

/**
 * @class_name:ConTest2019��12��31��
 * @comments:����clone�����������ƣ���Ҫ��д����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��31������10:48:05
 */
public class ConTest {

	/**
	 * 
	 */
	public ConTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��31������10:48:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Employee2 original = new Employee2("John Q. Public",50000);
			original.setHireDay(2000,1,1);
			Employee2 copy = original.clone();
			copy.raiseSalary(10);
			copy.setHireDay(2002,12,31);
			System.out.println("original="+original);
			System.out.println("copy="+copy);
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
	}

}
