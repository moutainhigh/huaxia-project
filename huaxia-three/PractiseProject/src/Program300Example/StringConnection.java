/**
 * Title: StringConnection.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������5:02:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:StringConnection2020��9��18��
 * @comments:�ַ�������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������5:02:22
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
	 *@Date: 2020��9��18������5:02:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "Hello"+", "+"world";
		System.out.println("ʹ�üӷ���������"+s1);
		StringBuffer sb2 = new StringBuffer();
		sb2.append("Hello");
		sb2.append(",");
		sb2.append(" ");
		sb2.append("world");
		String s2 =sb2.toString();
		 System.out.println("ʹ��append()���������"+sb2);
			StringBuffer sb3 = new StringBuffer().append("Hello").append(",").append(" ").append("world");
			 System.out.println("ʹ��append()����һ�ַ��������"+sb3.toString());
	}

}
