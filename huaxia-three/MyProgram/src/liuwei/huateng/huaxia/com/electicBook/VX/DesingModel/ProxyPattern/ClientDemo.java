/**
 * Title: ClientDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������3:48:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ProxyPattern;

/**
 * @class_name:ClientDemo2020��1��6��
 * @comments:���Դ���ģʽ������ģʽ���ǣ���ԭ�еĻ����ϼ�����ϸ���Ķ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������3:48:38
 */
public class ClientDemo {
	
	/**
	 * 
	 */
	public ClientDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��6������3:48:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IGamePlayer player = new GamePlayer("����ң");
		IGamePlayer proxy = new GamePlayerProxy(player);
		proxy.killBoss();
		proxy.upGrade();
	}

}
