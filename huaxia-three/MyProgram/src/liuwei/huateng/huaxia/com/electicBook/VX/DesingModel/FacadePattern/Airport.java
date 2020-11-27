/**
 * Title: Airport.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午5:05:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacadePattern;

/**
 * @class_name:Airport2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午5:05:48
 */
public class Airport {

	/**
	 * 
	 */
	public Airport() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: bookTicker
	 *@Description: TODO 飞机票
	 *@param from
	 *@param to
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午5:06:47
	 */
	public void bookTicker(String from ,String to){
		System.out.println("订购了从"+ from +"到"+to+"的机票");
	}
}
