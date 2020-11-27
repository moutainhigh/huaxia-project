/**
 * Title: TicketWindow.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��5������3:12:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:TicketWindow2019��12��5��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��5������3:12:36
 */
public class TicketWindow2 extends Thread {

	private final String name;
	
	//���������ٱ�ҵ��
	private static final int MAX= 50;
	
	private static int index = 1;
	
	public TicketWindow2(String name){
		this.name = name;		
	}
	@Override 
	public void run(){
		while(index < MAX){
			System.out.println("��̨"+name+"��ǰ�ĺ����ǣ�"+(index ++	));
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
	 *@Date: 2019��12��5������3:12:36
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketWindow2 ticketWindow1 = new TicketWindow2("һ�ų��Ż�");
		ticketWindow1.start();
		TicketWindow2 ticketWindow2 = new TicketWindow2("���ų��Ż�");
		ticketWindow2.start();
		TicketWindow2 ticketWindow3 = new TicketWindow2("���ų��Ż�");
		ticketWindow3.start();
		TicketWindow2 ticketWindow4 = new TicketWindow2("�ĺų��Ż�");
		ticketWindow4.start();
	}

}
