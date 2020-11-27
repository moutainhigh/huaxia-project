/**
 * Title: NextDay.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月13日下午2:45:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @class_name:NextDay2020年8月13日
 * @comments: 获得下一天的时间
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月13日下午2:45:14
 */
public class NextDay {

	/**
	 * Constructor
	 */
	public NextDay() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午2:45:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now = new Date();//获得当前时间
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//打印下一天的时间
		System.out.println(fomat.format(getNextDate(now)));
	}
	/**
	 * @Title: getNextDate
	 *@Description: TODO 获得下一天的时间
	 *@param d
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月13日下午2:46:24
	 */
	public static Date getNextDate(Date d){
		long addTime  =1;
		addTime *= 1;
		addTime *= 24;
		addTime *= 60;
		addTime *= 60;
		addTime *= 1000;
		//用毫秒数构造新的日期
		Date date = new Date(d.getTime() + addTime);
		return date;
	}
}
