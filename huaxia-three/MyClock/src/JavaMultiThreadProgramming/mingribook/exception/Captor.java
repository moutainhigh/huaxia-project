/**
 * Title: Captor.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午2:46:53
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.exception;

/**
 * @class_name:Captor2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午2:46:53
 */
public class Captor {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:46:53
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			int result = quotient(3,-1);
		}catch(MyException2 e){
			System.out.println(e.getMessage());
		}catch(ArithmeticException e){
			System.out.println("除数不能为0");
		}catch(Exception e){
			System.out.println("程序发生了其他的异常");
		}
	}
	/**
	 * @Title: quotient
	 *@Description: TODO
	 *@param x
	 *@param y
	 *@return
	 *@throws MyException2
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:48:23
	 */
	static int quotient(int x,int y) throws MyException2{
		if(y<0){
			throw new MyException2("除数不能是负数");
		}
		return x/y;
	}
}
