/**
 * Title: Shoot.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午2:34:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.exception;

/**
 * @class_name:Shoot2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午2:34:58
 */
public class Shoot {

	/**
	 * 
	 */
	public Shoot() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:34:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			pop();
		}catch(NegativeArraySizeException e){
			System.out.println("pop()方法抛出异常");
		}
	}
	/**
	 * @Title: pop
	 *@Description: TODO
	 *@throws NegativeArraySizeException
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:36:35
	 */
	static void pop() throws NegativeArraySizeException{
		int[] arr= new int[-3];
	} 
}
