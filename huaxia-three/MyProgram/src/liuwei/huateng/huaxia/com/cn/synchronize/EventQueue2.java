/**
 * Title: EventQueue.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��11������9:19:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.LinkedList;

/**
 * @class_name:EventQueue2019��12��11��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��11������9:19:51
 */
public class EventQueue2 {
	private  int max;
	static class Event{};
	private final LinkedList<Event> eventQueue = new LinkedList<>();
	private final static int DEFAULT_MAX_EVENT = 10;
	/**
	 * 
	 */
	public EventQueue2() {
		// TODO Auto-generated constructor stub
		this(DEFAULT_MAX_EVENT);
	}
	public EventQueue2(int max){
		this.max = max;
	}
	/**
	 * @Title: offer
	 *@Description: TODO�������д�����
	 *@param event
	 *@author: LiuWei
	 *@Date: 2019��12��11������9:30:47
	 */
	public void offer(Event event){
		synchronized(eventQueue)
		{
			while(eventQueue.size() >= max){
				if(eventQueue.size() > max){
					System.err.println(eventQueue.size());
					System.exit(0);
				}
				try{
					console(" the queue is full.");
				eventQueue.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			console(" the new event is submitted");
			eventQueue.addLast(event);
			if(eventQueue.size() > max){
				System.err.println(eventQueue.size());
				System.exit(0);
			}
			eventQueue.notify();
		}
	}
	/**
	 * @Title: take
	 *@Description: TODO������ȡ����
	 *@return
	 *@author: LiuWei
	 *@Date: 2019��12��11������9:34:03
	 */
	public Event take(){
		synchronized(eventQueue)
		{
			if(eventQueue.isEmpty()){
				try{
					console(" the queue is empty..");
				eventQueue.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			Event event = eventQueue.removeFirst();
			eventQueue.notify();
			console(" the event "+event+" is handled.");
			return event;
		}
	}
	/**
	 * @Title: console
	 *@Description: TODO 
	 *@param message
	 *@author: LiuWei
	 *@Date: 2019��12��11������9:37:02
	 */
	private void console(String message){
		System.out.printf("%s:%s\n",Thread.currentThread().getName(),message);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��11������9:19:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
