/**
 * Title: ListFonts.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��13������3:24:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter10;

import java.awt.GraphicsEnvironment;

/**
 * @class_name:ListFonts2020��1��13��
 * @comments: ��ӡϵͳ���е���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������3:24:47
 */
public class ListFonts {

	/**
	 * 
	 */
	public ListFonts() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��13������3:24:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(String fontName:fontNames){
			System.out.println(fontName);
		}
	}

}
