/**
 * Title: ValueOfEnum.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日上午11:03:12
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
enum MyColor4{红色,蓝色,绿色};
/**
 * @class_name:ValueOfEnum2020年8月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午11:03:12
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
	 *@Date: 2020年8月27日上午11:03:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyColor4 c = MyColor4.valueOf(MyColor4.class,"红色");
		System.out.println(c);
	}
}
