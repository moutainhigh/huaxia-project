/**
 * Title: TicketWindow.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��5������3:12:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:TicketWindow2019��12��5��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��5������3:12:36
 */
public class TicketWindow extends Thread {

	private final String name;
	
	//���������ٱ�ҵ��
	private static final int MAX= 50;
	
	private int index = 1;
	
	public TicketWindow(String name){
		this.name = name;		
	}
	@Override 
	public void run(){
		while(index < MAX){
			System.out.println("��̨"+name+"��ǰ�ĺ����ǣ�"+(index ++	));
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
		TicketWindow ticketWindow1 = new TicketWindow("һ�ų��Ż�");
		ticketWindow1.start();
		TicketWindow ticketWindow2 = new TicketWindow("���ų��Ż�");
		ticketWindow2.start();
		TicketWindow ticketWindow3 = new TicketWindow("���ų��Ż�");
		ticketWindow3.start();
		TicketWindow ticketWindow4 = new TicketWindow("�ĺų��Ż�");
		ticketWindow4.start();
	}

}
