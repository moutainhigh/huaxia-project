/**
 * Title: TemperatureConverter.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��21������11:13:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;

/**
 * @class_name:TemperatureConverter2020��9��21��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��21������11:13:32
 */
public class TemperatureConverter {
	/**
	 * @Title: getFahrenheit
	 *@Description: TODO ��ȡ�����¶ȵ�ֵ
	 *@param celsius
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��21������11:14:41
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
	 *@Date: 2020��9��21������11:13:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("������Ҫת�����¶ȣ���λ�����϶ȣ�");
		Scanner in = new Scanner(System.in);
		double celsius = in.nextDouble();
		TemperatureConverter converter= new TemperatureConverter();
		double fahrenheit = converter.getFahrenheit(celsius);
		System.out.println("ת����ɵ��¶ȣ���λ�����϶ȣ���"+fahrenheit);
	}

}
