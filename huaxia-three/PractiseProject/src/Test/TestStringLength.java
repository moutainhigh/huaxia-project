/**
 * Title: TestStringLength.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��24������7:44:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Test;

import java.io.UnsupportedEncodingException;

/**
 * @class_name:TestStringLength2020��10��24��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��24������7:44:54
 */
public class TestStringLength {

	/**
	 * Constructor
	 */
	public TestStringLength() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��10��24������7:44:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String a = "123abc";
//		try {
//			int num = a.getBytes("utf-8").length;
//			System.out.println(num);
//			a = "����";
//			num = a.getBytes("utf-8").length;
//			System.out.println(num);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String a = "123abc";
		try {
			int num = a.getBytes("gbk").length;
			System.out.println(num);
			a = "����";
			num = a.getBytes("gbk").length;
			System.out.println(num);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
