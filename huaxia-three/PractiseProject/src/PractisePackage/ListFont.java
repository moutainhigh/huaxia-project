package PractisePackage;

import java.awt.GraphicsEnvironment;
/**
 * ��ȡϵͳ����
 * @class_name:ListFont2020��8��5��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��5������4:02:40
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
