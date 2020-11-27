/**
 * Title: EnumSwitch.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日上午10:57:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;
/**
 * 
 * @class_name:MyColor2020年8月27日
 * @comments: 枚举类型的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午10:58:37
 */
enum MyColor{红色,绿色,蓝色};
/**
 * @class_name:EnumSwitch2020年8月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午10:57:39
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
	 *@Date: 2020年8月27日上午10:57:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyColor c1 = MyColor.绿色;
		switch(c1){
		case 红色:
		{
			System.out.println("我是红色！");
			break;
		}
		case 绿色:
		{
			System.out.println("我是绿色！");
			break;
		}
		case 蓝色:
		{
			System.out.println("我是蓝色！");
			break;
		}
		}
	}

}
