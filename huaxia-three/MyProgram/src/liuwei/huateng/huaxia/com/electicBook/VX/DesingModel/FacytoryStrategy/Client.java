/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午3:41:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class_name:Client2020年1月8日
 * @comments:策略模式和工厂模式的混用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午3:41:47
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
	 *@Date: 2020年1月8日下午3:41:47
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//初始化一张IC卡
		Card card = new Card("100010",350,500);
		System.out.println("==================IC卡初始化信息=========================");
		card.showCard();
		String choice = "N";
		//选择Y就退出
		while(!choice.equalsIgnoreCase("y")){
			//创建一个交易对象
			Trade trade= new Trade();
			System.out.println("请输入交易编号");
			trade.setTradeNo(getInput());
			System.out.println("请输入交易金额：");
			trade.setAmount(Double.parseDouble(getInput()));
			//进行交易，执行扣款操作
			DeductionFacade.deduct(card, trade);
			//显示交易后IC卡中的信息
			card.showCard();
			System.out.println("是否退出系统：(Y/N):");
			choice = getInput();
		}
	}
	/**
	 * @Title: getInput
	 *@Description: TODO 
	 *从键盘输入数据
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午3:46:39
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
