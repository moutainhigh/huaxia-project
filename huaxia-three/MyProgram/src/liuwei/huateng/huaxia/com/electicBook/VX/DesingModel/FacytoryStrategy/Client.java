/**
 * Title: Client.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������3:41:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class_name:Client2020��1��8��
 * @comments:����ģʽ�͹���ģʽ�Ļ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������3:41:47
 */
public class Client {

	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��8������3:41:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//��ʼ��һ��IC��
		Card card = new Card("100010",350,500);
		System.out.println("==================IC����ʼ����Ϣ=========================");
		card.showCard();
		String choice = "N";
		//ѡ��Y���˳�
		while(!choice.equalsIgnoreCase("y")){
			//����һ�����׶���
			Trade trade= new Trade();
			System.out.println("�����뽻�ױ��");
			trade.setTradeNo(getInput());
			System.out.println("�����뽻�׽�");
			trade.setAmount(Double.parseDouble(getInput()));
			//���н��ף�ִ�пۿ����
			DeductionFacade.deduct(card, trade);
			//��ʾ���׺�IC���е���Ϣ
			card.showCard();
			System.out.println("�Ƿ��˳�ϵͳ��(Y/N):");
			choice = getInput();
		}
	}
	/**
	 * @Title: getInput
	 *@Description: TODO 
	 *�Ӽ�����������
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��8������3:46:39
	 */
	public static  String getInput(){
		String str = "";
		try{
			str = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return str;
	}
}
