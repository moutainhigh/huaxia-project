/**
 * Title: TestColor2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������2:55:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;
/**
 * @class_name:NewColor22020��8��27��
 * @comments:NewColor2 ö�����͵�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������3:00:58
 */
enum NewColor2 implements ColorInterface
{
	��ɫ{
		@Override
		public String getColor() {
			// TODO Auto-generated method stub
			return "RED";
		}
	},��ɫ{
		@Override
		public String getColor() {
			// TODO Auto-generated method stub
			return "GREEN";
		}
	},��ɫ{
		@Override
		public String getColor() {
			// TODO Auto-generated method stub
			return "BLUE";
		}
	};
}
/**
 * @class_name:TestColor22020��8��27��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������2:55:06
 */
public class TestColor2 {

	/**
	 * Constructor
	 */
	public TestColor2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��27������2:55:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(NewColor2 c:NewColor2.values())
		{
			System.out.println(c.ordinal()+"-->"+c.name()+": "+c.getColor());
		}
	}

}
