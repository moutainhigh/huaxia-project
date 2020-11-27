/**
 * Title: Exp10.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月21日下午3:50:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.io.IOException;

/**
 * @class_name:Exp102020年8月21日
 * @comments:测试异常的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日下午3:50:13
 */
public class Exp10 {

	/**
	 * Constructor
	 */
	public Exp10() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月21日下午3:50:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			System.out.println("...正在运行程序....."	);
			throw new IOException("用户自行产生的异常");
		}catch(IOException e){
			System.out.println("已捕获了改异常！");
			e.printStackTrace();
		}
	}

}
