/**
 * Title: MyException2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午2:45:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.exception;

/**
 * @class_name:MyException22019年10月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午2:45:36
 */
public class MyException2 extends Exception {
	String message;
	/**
	 * 
	 */
	public MyException2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public MyException2(String arg0) {
		super(arg0);
		message = arg0;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public MyException2(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MyException2(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public MyException2(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
