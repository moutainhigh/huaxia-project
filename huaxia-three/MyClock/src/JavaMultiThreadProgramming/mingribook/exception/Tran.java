/**
 * Title: Tran.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午2:29:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.exception;

/**
 * @class_name:Tran2019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午2:29:49
 */
public class Tran {

	/**
	 * 
	 */
	public Tran() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:29:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			int result = avg(102,150);
			System.out.println(result);
		}catch(MyException e){
			System.out.println(e);
		}
	}
	/**
	 * @Title: avg
	 *@Description: TODO
	 *@param number1
	 *@param number2
	 *@return
	 *@throws MyException
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:32:18
	 */
	static int avg(int number1 ,int number2) throws MyException{
		if(number1<0 || number2<0){
			throw new MyException("不可以使用负数");
		}
		if(number1>100 || number2>100){
			throw new MyException("数值太大了");
		}
		return (number1+number2)/2;
	}
}
