/**
 * Title: Queue.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��28������3:56:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:Queue2020��9��28��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��28������3:56:12
 */
public class Queue<E> {
	private Object[] data = null;
	private int maxSize ;//��������
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
			throw new RuntimeException("��ʼ����С����С��0"+initialSize);
		}
	}
	/**
	 * @Title: add
	 *@Description: TODO
	 *@param e
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��28������4:01:03
	 */
	public boolean add(E e){
		if(rear == maxSize){
			throw new RuntimeException("�����Ѿ����ˣ��޷������µ�Ԫ�أ�");
		}else{
			data[rear++] = e;
			return true;
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��28������3:56:12
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
