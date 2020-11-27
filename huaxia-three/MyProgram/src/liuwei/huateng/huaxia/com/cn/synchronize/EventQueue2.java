/**
 * Title: EventQueue.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月11日上午9:19:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.LinkedList;

/**
 * @class_name:EventQueue2019年12月11日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月11日上午9:19:51
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
	 *@Description: TODO往队列中存数据
	 *@param event
	 *@author: LiuWei
	 *@Date: 2019年12月11日上午9:30:47
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
	 *@Description: TODO队列中取数据
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年12月11日上午9:34:03
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
	 *@Date: 2019年12月11日上午9:37:02
	 */
	private void console(String message){
		System.out.printf("%s:%s\n",Thread.currentThread().getName(),message);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月11日上午9:19:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
