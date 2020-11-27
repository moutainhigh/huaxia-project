/**
 * Title: TestColor2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日下午2:55:06
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;
/**
 * @class_name:NewColor22020年8月27日
 * @comments:NewColor2 枚举类型的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日下午3:00:58
 */
enum NewColor2 implements ColorInterface
{
	红色{
		@Override
		public String getColor() {
			// TODO Auto-generated method stub
			return "RED";
		}
	},绿色{
		@Override
		public String getColor() {
			// TODO Auto-generated method stub
			return "GREEN";
		}
	},蓝色{
		@Override
		public String getColor() {
			// TODO Auto-generated method stub
			return "BLUE";
		}
	};
}
/**
 * @class_name:TestColor22020年8月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日下午2:55:06
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
	 *@Date: 2020年8月27日下午2:55:06
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(NewColor2 c:NewColor2.values())
		{
			System.out.println(c.ordinal()+"-->"+c.name()+": "+c.getColor());
		}
	}

}
