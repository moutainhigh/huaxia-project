/**
 * Title: MouseTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月2日上午11:42:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.csdn.test;

import java.awt.AWTException;
import java.awt.Robot;

/**
 * @class_name:MouseTest2020年1月2日
 * @comments:Java恶搞程序，鼠标移动
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月2日上午11:42:00
 */
public class MouseTest {

	/**
	 * 
	 */
	public MouseTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月2日上午11:42:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Robot m1 = new Robot();
			for(int i=0;i<100;i++)
			{
				m1.mouseMove((int)(Math.random()*1000), (int)(Math.random()*1000));
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
