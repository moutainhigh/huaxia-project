/**
 * Title: StackTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������11:23:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:StackTest2020��9��18��
 * @comments:ջ���ݽṹ�Ĳ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������11:23:00
 */
public class StackTest {
	
	/**
	 * Constructor
	 */
	public StackTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������11:23:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack2<String> stack = new Stack2<String>();
		System.out.println("��ջ�������ַ���");
		System.out.println("��������");
		System.out.println("���μ�");
		System.out.println("ˮ䰴�");
		System.out.println("��¥��");
		stack.push("��������");
		stack.push("���μ�");
		stack.push("ˮ䰴�");
		stack.push("��¥��");
		System.out.println("��ջ��ȡ���ַ���");
		while(!stack.empty()){
			System.out.println((String)stack.pop());
		}
	}

}
