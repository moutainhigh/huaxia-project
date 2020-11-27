/**
 * Title: NextDay.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��13������2:45:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @class_name:NextDay2020��8��13��
 * @comments: �����һ���ʱ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��13������2:45:14
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
	 *@Date: 2020��8��13������2:45:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now = new Date();//��õ�ǰʱ��
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//��ӡ��һ���ʱ��
		System.out.println(fomat.format(getNextDate(now)));
	}
	/**
	 * @Title: getNextDate
	 *@Description: TODO �����һ���ʱ��
	 *@param d
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��13������2:46:24
	 */
	public static Date getNextDate(Date d){
		long addTime  =1;
		addTime *= 1;
		addTime *= 24;
		addTime *= 60;
		addTime *= 60;
		addTime *= 1000;
		//�ú����������µ�����
		Date date = new Date(d.getTime() + addTime);
		return date;
	}
}
