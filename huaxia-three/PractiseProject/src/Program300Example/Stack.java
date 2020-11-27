/**
 * Title: Stack.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������11:16:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.LinkedList;

/**
 * @class_name:Stack2020��9��18��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������11:16:19
 */
public class Stack<T> {
	private LinkedList<T> container = new LinkedList<T>();	
	/**
	 * Constructor
	 */
	public Stack() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������11:16:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * @Title: push
	 *@Description: TODO ��ջ
	 *@param t
	 *@author: LiuWei
	 *@Date: 2020��9��18������11:18:46
	 */
	public void push(T t){
		container.addFirst(t);
	}
	/**
	 * @Title: pop
	 *@Description: TODO
	 *@return ����
	 *@author: LiuWei
	 *@Date: 2020��9��18������11:19:44
	 */
	public T pop(){
		return container.removeFirst();
	}
	/**
	 * @Title: empty
	 *@Description: TODO
	 *@return �ж��Ƿ�Ϊ��
	 *@author: LiuWei
	 *@Date: 2020��9��18������11:20:14
	 */
	public boolean empty(){
		return container.isEmpty();
	}
}
