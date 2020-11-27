package DataStructuresAndAlgorithm;
/**
 * 链表节点 前后节点
 * @author liuwei
 *
 */
public class Node<Integer>{
	public Node(Integer d,Node<Integer> p,Node<Integer> n){
		data = d;
		prev = p;
		next = n;
	}
	public Integer data;
	public Node<Integer> prev;
	public Node<Integer> next;
}
