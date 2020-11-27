/**
 * Title: GlobalNum.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��3������3:50:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:GlobalNum2020��1��3��
 * @comments:��ʽ�ĵ���ģʽ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��3������3:50:00
 */
public class GlobalNum {
	private static GlobalNum gn = new GlobalNum();
	private int num = 0;
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��3������3:51:05
	 */
	public static GlobalNum getInstance(){
		return gn;
	}
	/**
	 * @Title: getNum
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��3������3:51:32
	 */
	public synchronized int getNum(){
		return ++num;
	}
}
