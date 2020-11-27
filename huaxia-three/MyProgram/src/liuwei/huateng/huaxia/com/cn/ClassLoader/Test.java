/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月12日上午10:23:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ClassLoader;

/**
 * @class_name:Test2019年12月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月12日上午10:23:18
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月12日上午10:23:18
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("liuwei.huateng.huaxia.com.cn.ClassLoader.Simple");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
