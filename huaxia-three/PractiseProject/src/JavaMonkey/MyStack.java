/**
 * Title: MyStack.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��17������2:03:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:MyStack2020��9��17��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��17������2:03:36
 */
public class MyStack {
	private int capicity = 1;
	private int point = -1;
	private String[] buffer;
	/**
	 * Constructor
	 */
	public MyStack() {
		// TODO Auto-generated constructor stub
		this(1000);
	}
	public MyStack(int capicity) {
		// TODO Auto-generated constructor stub
		this.capicity = capicity;
		buffer = new String[capicity];
	}
	/**
	 * @Title: push
	 *@Description: TODO
	 *@param data
	 *@author: LiuWei
	 *@Date: 2020��9��17������2:06:19
	 */
	public void push(String data){
		if(point == capicity - 1){
			System.out.println("��ջ�Ѿ����ˡ�����");
			return;
		}
			if(data == null){
				System.out.println("���������ջ����null");
				return;
			}
		buffer[++point] = data;
	}
	/**
	 * @Title: pop
	 *@Description: TODO
	 *@return ����Ԫ��
	 *@author: LiuWei
	 *@Date: 2020��9��17������2:08:57
	 */
	public String pop(){
		if(point == -1){
			System.out.println("��ջ�ǿյġ���");
			return null;
		}
		return buffer[point--];
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��17������2:03:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyStack stack =new MyStack();
		stack.push("Tom");
		stack.push("Mike");
		stack.push("Mary");
		stack.push("Rose");
		for(int i=0;i<4;i++){
			System.out.println(stack.pop());
		}
	}
}
