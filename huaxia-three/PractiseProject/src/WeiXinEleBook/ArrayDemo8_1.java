/**
 * Title: ArrayDemo8_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午5:30:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

/**
 * @class_name:ArrayDemo8_12020年9月11日
 * @comments: 数组的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午5:30:13
 */
public class ArrayDemo8_1 {

	/**
	 * Constructor
	 */
	public ArrayDemo8_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午5:30:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] month = {31,28,31,30,31,30,31,31,30,31,30,31};
		for(int i=0;i<month.length;i++){
			System.out.println("第"+(i+1)+"月有"+month[i]+"天");
		}
	}

}
