/**
 * Title: EnumTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��30������2:55:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter5;

import java.util.Scanner;

/**
 * @class_name:EnumTest2019��12��30��
 * @comments: ����ö�����͵�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��30������2:55:09
 */
public class EnumTest {

	/**
	 * 
	 */
	public EnumTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��30������2:55:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in =new Scanner(System.in);
		System.out.println("Enter a size:(SMALL,MEDIUM,LARGE,EXTRA_LARGE)");
		String input = in.next().toUpperCase();
		Size size = Enum.valueOf(Size.class, input);
		System.out.println("size="+size);
		System.out.println("abbreviation="+size.getAbbreviation());
		if(size == Size.EXTRA_LARGE)
			System.out.println("Good job--you paid attenetion to the _.");
	}

}
enum Size
{
	SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");
	private Size(String abbreviation){
		this.abbreviation = abbreviation;
	}
	private String abbreviation;
	public String getAbbreviation(){
		return abbreviation;
	}
}
