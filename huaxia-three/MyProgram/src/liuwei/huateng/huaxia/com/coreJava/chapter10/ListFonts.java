/**
 * Title: ListFonts.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日下午3:24:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter10;

import java.awt.GraphicsEnvironment;

/**
 * @class_name:ListFonts2020年1月13日
 * @comments: 打印系统所有的字体名称
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日下午3:24:47
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
	 *@Date: 2020年1月13日下午3:24:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(String fontName:fontNames){
			System.out.println(fontName);
		}
	}

}
