/**
 * Title: ClientDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午4:32:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.AdapterPattern;

/**
 * @class_name:ClientDemo2020年1月6日
 * @comments:适配器模式，将水饺转化为混沌，继承关系
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午4:32:06
 */
public class ClientDemo {

	/**
	 * 
	 */
	public ClientDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午4:32:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hundun d = new FoodAdapter();
		d.makeHundun();
	}

}
