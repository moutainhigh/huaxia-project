/**
 * Title: TemperatureConverter.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日上午11:13:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:TemperatureConverter2020年9月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日上午11:13:32
 */
public class TemperatureConverter {
	/**
	 * @Title: getFahrenheit
	 *@Description: TODO 获取华氏温度的值
	 *@param celsius
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月21日上午11:14:41
	 */
	public double getFahrenheit(double  celsius){
		double fahrenheit = 1.8*celsius+32;
		return fahrenheit; 
	}
	/**
	 * Constructor
	 */
	public TemperatureConverter() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月21日上午11:13:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请输入要转换的温度（单位：摄氏度）");
		Scanner in = new Scanner(System.in);
		double celsius = in.nextDouble();
		TemperatureConverter converter= new TemperatureConverter();
		double fahrenheit = converter.getFahrenheit(celsius);
		System.out.println("转换完成的温度（单位：华氏度）："+fahrenheit);
	}

}
