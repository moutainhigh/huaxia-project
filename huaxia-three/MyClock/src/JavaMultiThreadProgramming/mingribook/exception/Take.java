/**
 * Title: Take.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月14日下午2:25:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.exception;

/**
 * @class_name:Take2019年10月14日
 * @comments:使用trycatch，可以是程序正常的运行和执行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月14日下午2:25:52
 */
public class Take {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月14日下午2:25:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			String str = "lili";
			System.out.println(str+"年龄是：");
			int age = Integer.parseInt("20L");
			System.out.println(age);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("program over");
	}

}
