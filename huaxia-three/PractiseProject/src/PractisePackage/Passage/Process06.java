/**
 * Title: Process06.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������11:05:40
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process062020��8��18��
 * @comments:˵��trycatchfinally�����һ��� finall���һ����ִ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������11:05:40
 */
public class Process06 {

	/**
	 * Constructor
	 */
	public Process06() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��18������11:05:40
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getNum1());
		System.out.println(getNum2());
	}
	/**
	 * @Title: getNum1
	 *@Description: TODO getNum1 ��Ȼ��� 100��˵��trycatchfinally�����һ���
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��18������11:07:09
	 */
	public static int getNum1(){
		int a = 100;
		try{
			return a+1;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("����getNum1�᲻�������");
			return a;
		}
	}
	/**
	 * @Title: getNum2
	 *@Description: TODO
	 *@return getNum1 ��Ȼ��� 100��˵��trycatchfinally�����һ���
	 *@author: LiuWei 
	 *@Date: 2020��8��18������11:09:42
	 */
	public static int getNum2(){
		int a = 100;
		try{
			return a++;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("����getNum2�᲻�������");
			return a;
		}
	}
}
