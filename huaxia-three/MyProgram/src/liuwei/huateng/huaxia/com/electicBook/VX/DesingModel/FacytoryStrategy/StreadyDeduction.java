/**
 * Title: StreadyDeduction.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午3:11:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:StreadyDeduction2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午3:11:12
 */
public class StreadyDeduction implements IDeduction {

	/**
	 * 
	 */
	public StreadyDeduction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy.IDeduction#exec(liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy.Card, liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy.Trade)
	 */
	@Override
	public boolean exec(Card card, Trade trade) {
		// TODO Auto-generated method stub
		//获取交易金额
		double m = trade.getAmount();
		//获取IC卡中的固定金额
		double s = card.getSteadyMoney();
		//获取IC卡中的自由金额
		double f = card.getFreeMoney();
		if(m>s+f){
			return false;
		}else{
			if(s>m){
				card.setSteadyMoney(s-m);
				return true;
			}else{
				//从固定金额中扣除，然后从自由金额中扣除
				card.setSteadyMoney(0);
				card.setFreeMoney(f+s-m);
				return true;
			}
		}
	}

}
