/**
 * Title: EnumSwitch.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������10:57:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;
/**
 * 
 * @class_name:MyColor2020��8��27��
 * @comments: ö�����͵�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������10:58:37
 */
enum MyColor{��ɫ,��ɫ,��ɫ};
/**
 * @class_name:EnumSwitch2020��8��27��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������10:57:39
 */
public class EnumSwitch {

	/**
	 * Constructor
	 */
	public EnumSwitch() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��27������10:57:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyColor c1 = MyColor.��ɫ;
		switch(c1){
		case ��ɫ:
		{
			System.out.println("���Ǻ�ɫ��");
			break;
		}
		case ��ɫ:
		{
			System.out.println("������ɫ��");
			break;
		}
		case ��ɫ:
		{
			System.out.println("������ɫ��");
			break;
		}
		}
	}

}
