/**
 * Title: Process06.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日上午11:05:40
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process062020年8月18日
 * @comments:说明trycatchfinally语句是一块的 finall语句一定会执行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日上午11:05:40
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
	 *@Date: 2020年8月18日上午11:05:40
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getNum1());
		System.out.println(getNum2());
	}
	/**
	 * @Title: getNum1
	 *@Description: TODO getNum1 竟然输出 100，说明trycatchfinally语句是一块的
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午11:07:09
	 */
	public static int getNum1(){
		int a = 100;
		try{
			return a+1;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("测试getNum1会不会输出！");
			return a;
		}
	}
	/**
	 * @Title: getNum2
	 *@Description: TODO
	 *@return getNum1 竟然输出 100，说明trycatchfinally语句是一块的
	 *@author: LiuWei 
	 *@Date: 2020年8月18日上午11:09:42
	 */
	public static int getNum2(){
		int a = 100;
		try{
			return a++;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("测试getNum2会不会输出！");
			return a;
		}
	}
}
