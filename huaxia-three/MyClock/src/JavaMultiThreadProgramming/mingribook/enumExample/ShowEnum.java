/**
 * Title: ShowEnum.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日上午9:50:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.enumExample;

/**
 * @class_name:ShowEnum2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日上午9:50:20
 */
public class ShowEnum {
	/**
	 * 
	 * @class_name:Constants22019年10月16日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2019年10月16日上午9:56:26
	 */
	enum Constants2{
		Constants_A,Constants_B
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日上午9:50:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<Constants2.values().length;i++){
			System.out.println("枚举类型成员变量："+Constants2.values()[i]);
		}
	}

}
