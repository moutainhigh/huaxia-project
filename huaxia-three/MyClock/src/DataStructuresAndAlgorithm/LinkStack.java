package DataStructuresAndAlgorithm;
/**
 * 链表类型的栈数据结构
 * @author liuwei
 *
 */
public class LinkStack {
	public static class Node{
		Integer data;
		Node next;
	}
	/**
	 * @Title: InitStack
	 *@Description: 初始化链栈
	 *@param node
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午12:43:19
	 */
	void InitStack(Node node){
		node= null;
	}
	/**
	 * @Title: Push
	 *@Description: 入栈的方法
	 *@param top
	 *@param x
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午12:46:33
	 */
	void Push(Node top,Integer x){
//		int temp;
		Node node = new Node();
		node.data = top.data;
		node.next = top.next;
		top.next = node;
		top.data = x;
//		System.out.println(top.data);
//		if(top.next != null)
//		System.out.println(top.next.data);
	}
	/**
	 * @Title: pop
	 *@Description: 出栈方法
	 *@param top
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午12:47:32
	 */
	Integer pop(Node top){
		if(top == null){
			System.out.println("下溢");
			System.exit(0);
		}
		int x = top.data;
		Node p = top;
		top.data = top.next.data;
		top.next = top.next.next;
		return x;
	}
	/**
	 * @Title: empty
	 *@Description: 判断链栈是否为空
	 *@param top
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午12:50:35
	 */
	boolean empty(Node top){
		if(top == null) return true;
		else return false;
	}
	public static void main(String args[]){
		LinkStack link = new LinkStack();
		Node node = new Node();
//		link.InitStack(node);
		for(int i=0;i<100;i++)
			link.Push(node, i+1);
		for(int i=0;i<100;i++)
			System.out.print(link.pop(node)+ " ");
		System.out.println();
	}
}
