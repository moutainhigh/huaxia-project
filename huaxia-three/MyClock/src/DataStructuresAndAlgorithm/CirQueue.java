package DataStructuresAndAlgorithm;
/**
 * 循环队列的实现
 * 队列是一种先进先出的数据结构
 * @author liuwei
 *
 */
public class CirQueue {
	private static int queueSize ;
	Integer[] data ;
	int front,rear;
	public CirQueue(int size){
		queueSize = size;
		data = new Integer[queueSize];
		initQueue(this);
	}
	/**
	 * @Title: initQueue
	 *@Description: 队列初始化
	 *@param q
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午3:01:10
	 */
	void initQueue(CirQueue q){
		q.front = q.rear = q.queueSize -1;
	}
	/**
	 * @Title: EnQueue
	 *@Description: 入队列
	 *@param q
	 *@param x
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午4:29:22
	 */
	void EnQueue(CirQueue q,Integer x){
		if((q.rear+1)%queueSize == q.front){
			System.out.println("上溢");
			System.exit(0);
		}
//		System.out.println(x);
		q.rear = (q.rear+1)%queueSize;
		q.data[q.rear] = x;
	}
	/**
	 * @Title: Dequeue
	 *@Description: 元素出队列，先进先出
	 *@param q
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午4:33:53
	 */
	Integer Dequeue(CirQueue q){
		if(q.rear == q.front){
			System.out.println("下溢");
			System.exit(0);
		}
		q.front =(q.front+1)%queueSize;
		return q.data[q.front];
	}
	/**
	 * @Title: GetFront
	 *@Description: 取出对头元素
	 *@param q
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午4:36:56
	 */
	Integer GetFront(CirQueue q){
		if(q.rear == q.front){
			System.out.println("下溢");
			System.exit(0);
		}
		int i=(q.front+1)%queueSize;
		return q.data[i];
	}
	/**
	 * @Title: empty
	 *@Description: 判断队列是否为空
	 *@param q
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年7月12日下午4:38:02
	 */
	boolean empty(CirQueue q){
		if(q.rear == q.front) return true;
		return false;
	}
	public static void main(String args[]){
		CirQueue queue = new CirQueue(1000);
		for(int i=0;i<queue.queueSize-1;i++){
			queue.EnQueue(queue, i+1);
		}
		for(int i=0;i<100;i++){
			System.out.print(queue.Dequeue(queue)+" ");
		}
		System.out.println();
	}
}
