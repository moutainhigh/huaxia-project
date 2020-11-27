/**
 * Title: Queue.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月28日下午3:56:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Queue2020年9月28日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月28日下午3:56:12
 */
public class Queue<E> {
	private Object[] data = null;
	private int maxSize ;//队列容量
	private int front;
	private int rear;
	/**
	 * Constructor
	 */
	public Queue() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param initialSizeConstructor
	 */
	public Queue(int initialSize){
		if(initialSize >=0){
			this.maxSize = initialSize;
			data = new Object[initialSize];
			front = rear = 0;
		}else{
			throw new RuntimeException("初始化大小不能小于0"+initialSize);
		}
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param e
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午4:01:03
	 */
	public boolean add(E e){
		if(rear == maxSize){
			throw new RuntimeException("队列已经满了，无法插入新的元素！");
		}else{
			data[rear++] = e;
			return true;
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月28日下午3:56:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
