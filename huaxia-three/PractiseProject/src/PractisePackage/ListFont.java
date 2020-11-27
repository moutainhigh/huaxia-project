package PractisePackage;

import java.awt.GraphicsEnvironment;
/**
 * 获取系统字体
 * @class_name:ListFont2020年8月5日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月5日下午4:02:40
 */
public class ListFont {
	public ListFont() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		System.out.println(fontNames.length);
		for(String fontName:fontNames){
			System.out.println(fontName);
		}
	}
}
