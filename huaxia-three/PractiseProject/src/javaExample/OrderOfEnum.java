/**
 * Title: OrderOfEnum.java
 * Description: ���ö�����͵�ÿһ������ı��
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������11:07:47
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
enum MyColor3{��ɫ,��ɫ,��ɫ};
/**
 * @class_name:OrderOfEnum2020��8��27��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������11:07:47
 */
public class OrderOfEnum {

	/**
	 * Constructor
	 */
	public OrderOfEnum() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��27������11:07:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyColor3[] allColor = MyColor3.values();
		for(MyColor3 aColor:allColor){
			System.out.println(aColor.name()+"--->"+aColor.ordinal());
		}
	}

}
