/**
 * Title: TicketWindow.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午3:12:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:TicketWindow2019年12月5日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午3:12:36
 */
public class TicketWindow extends Thread {

	private final String name;
	
	//最多受理多少笔业务
	private static final int MAX= 50;
	
	private int index = 1;
	
	public TicketWindow(String name){
		this.name = name;		
	}
	@Override 
	public void run(){
		while(index < MAX){
			System.out.println("柜台"+name+"当前的号码是："+(index ++	));
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
		TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
		ticketWindow1.start();
		TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
		ticketWindow2.start();
		TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
		ticketWindow3.start();
		TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
		ticketWindow4.start();
	}

}
