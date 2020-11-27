/**
 * Title: Process07.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日上午11:12:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Process072020年8月18日
 * @comments:break用于退出循环，不是当前循环
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日上午11:12:47
 */
public class Process07 {

	/**
	 * Constructor
	 */
	public Process07() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午11:12:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i= 1;i<3;i++){
			if(i == 2){
				break;
			}
			System.out.println(" i = "+i);
		}
	}

}
