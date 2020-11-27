/**
 * Title: StringConnection.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午5:02:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:StringConnection2020年9月18日
 * @comments:字符串函数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午5:02:22
 */
public class StringConnection {

	/**
	 * Constructor
	 */
	public StringConnection() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午5:02:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "Hello"+", "+"world";
		System.out.println("使用加法运算符输出"+s1);
		StringBuffer sb2 = new StringBuffer();
		sb2.append("Hello");
		sb2.append(",");
		sb2.append(" ");
		sb2.append("world");
		String s2 =sb2.toString();
		 System.out.println("使用append()方法输出："+sb2);
			StringBuffer sb3 = new StringBuffer().append("Hello").append(",").append(" ").append("world");
			 System.out.println("使用append()的另一种方法输出："+sb3.toString());
	}

}
