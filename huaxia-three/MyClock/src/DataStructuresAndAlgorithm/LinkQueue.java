package DataStructuresAndAlgorithm;
/**
 * 连式队列，先进先出
 * @author liuwei
 *
 */
public class LinkQueue {
	Integer data;
	LinkQueue next;
	LinkQueue front,rear;
	/**
	 * @Title: InitQueue
	 *@Description: 初始化链表
	 *@param q
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午4:57:57
	 */
	void InitQueue(LinkQueue q){
		q = new LinkQueue();
		q.next = null;
		q.front = q.rear;
		q.data  =0;
	}
	void EnQueue(LinkQueue q,Integer x){
		LinkQueue link = new LinkQueue();
		link.data  = x;
		link.next = null;
		q.next = link;
		q.rear = link;
	}
}
