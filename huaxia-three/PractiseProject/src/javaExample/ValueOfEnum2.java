/**
 * Title: ValueOfEnum.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������11:03:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;
/**
 * 
 * @class_name:MyColor2020��8��27��
 * @comments:value��ȡ����ֵ 
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������11:04:26
 */
enum MyColor4{��ɫ,��ɫ,��ɫ};
/**
 * @class_name:ValueOfEnum2020��8��27��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������11:03:12
 */
public class ValueOfEnum2 {

	/**
	 * Constructor
	 */
	public ValueOfEnum2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��27������11:03:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyColor4 c = MyColor4.valueOf(MyColor4.class,"��ɫ");
		System.out.println(c);
	}
}
