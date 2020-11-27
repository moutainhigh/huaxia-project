/**
 * Title: OrderOfEnum.java
 * Description: 输出枚举类型的每一个对象的编号
 * @autor:刘伟/liuwei
 * @date 2020年8月27日上午11:07:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;
/**
 * 
 * @class_name:MyColor2020年8月27日
 * @comments:value获取对象值
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午11:04:26
 */
enum MyColor3{红色,蓝色,绿色};
/**
 * @class_name:OrderOfEnum2020年8月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午11:07:47
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
	 *@Date: 2020年8月27日上午11:07:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyColor3[] allColor = MyColor3.values();
		for(MyColor3 aColor:allColor){
			System.out.println(aColor.name()+"--->"+aColor.ordinal());
		}
	}

}
