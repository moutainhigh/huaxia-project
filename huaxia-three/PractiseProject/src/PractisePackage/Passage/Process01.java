/**
 * Title: Process01.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日上午10:05:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process012020年8月18日
 * @comments: 计算if..else条件  if..(if..else...)else...(if...else...)
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日上午10:05:55
 */
public class Process01 {

	/**
	 * Constructor
	 */
	public Process01() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午10:05:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(compare01(40,30)){
			System.out.println("40>30:true");
		}else{
			System.out.println("40>30:false");
		}
		if(compare01(10,20) && compare01(20,30)){
			System.out.println("条件成立");
		}else{
			System.out.println("条件不成立");
		}
		if(compare01(20,10) || compare01(20,30)){
			System.out.println("条件成立");
		}else{
			System.out.println("条件不成立");
		}
		if(compare02(1,1)){
			if(compare02(2,2)){
				System.out.println("Running....");
			}
		}
		if(compare01(1,2)){
			if(compare01(5,3)){
				System.out.println("5>3:true");
			}
		}
	}
	/**
	 * @Title: compare01
	 *@Description: TODO 判断两个数的大小
	 *@param num1
	 *@param num2
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午10:07:47
	 */
	private static boolean compare01(int num1,int num2)
	{
		System.out.println("判断：num1="+num1+";num2="+num2);
		return num1 > num2;
	}
	private static boolean compare02(int num1,int num2)
	{
		System.out.println("判断：num1="+num1+";num2="+num2);
		return num1 == num2;
	}
}
