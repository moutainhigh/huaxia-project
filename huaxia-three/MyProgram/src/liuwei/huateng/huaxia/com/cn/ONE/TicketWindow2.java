/**
 * Title: TicketWindow.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午3:12:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:TicketWindow2019年12月5日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午3:12:36
 */
public class TicketWindow2 extends Thread {

	private final String name;
	
	//最多受理多少笔业务
	private static final int MAX= 50;
	
	private static int index = 1;
	
	public TicketWindow2(String name){
		this.name = name;		
	}
	@Override 
	public void run(){
		while(index < MAX){
			System.out.println("柜台"+name+"当前的号码是："+(index ++	));
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午3:12:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketWindow2 ticketWindow1 = new TicketWindow2("一号出号机");
		ticketWindow1.start();
		TicketWindow2 ticketWindow2 = new TicketWindow2("二号出号机");
		ticketWindow2.start();
		TicketWindow2 ticketWindow3 = new TicketWindow2("三号出号机");
		ticketWindow3.start();
		TicketWindow2 ticketWindow4 = new TicketWindow2("四号出号机");
		ticketWindow4.start();
	}

}
