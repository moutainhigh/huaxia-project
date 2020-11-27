package ThinkInJava;

import java.util.LinkedList;
/**k
 * 栈型结构，使用List实现栈结构
 * @author User
 *
 * @param <T>
 */
public class StackL<T> {
	private LinkedList<T> list = new LinkedList<T>();
	public void push(T v){
		list.addFirst(v);
	}
	public T top(){
		return list.getFirst();
	}
	public T pop(){
		return list.removeFirst();
	}
}
