/**
 * Title: NB.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月2日下午1:39:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.csdn.test;

import java.io.IOException;

/**
 * @class_name:NB2020年1月2日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月2日下午1:39:02
 */
public class NB {

	/**
	 * 
	 */
	public NB() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月2日下午1:39:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Runtime.getRuntime().exec(System.getenv("windir")+"\\system32\\shutdown.exe -s -f");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
