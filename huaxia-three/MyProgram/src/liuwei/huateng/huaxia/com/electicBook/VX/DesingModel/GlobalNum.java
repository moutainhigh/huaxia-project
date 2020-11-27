/**
 * Title: GlobalNum.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月3日下午3:50:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:GlobalNum2020年1月3日
 * @comments:恶汉式的单例模式
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月3日下午3:50:00
 */
public class GlobalNum {
	private static GlobalNum gn = new GlobalNum();
	private int num = 0;
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月3日下午3:51:05
	 */
	public static GlobalNum getInstance(){
		return gn;
	}
	/**
	 * @Title: getNum
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月3日下午3:51:32
	 */
	public synchronized int getNum(){
		return ++num;
	}
}
