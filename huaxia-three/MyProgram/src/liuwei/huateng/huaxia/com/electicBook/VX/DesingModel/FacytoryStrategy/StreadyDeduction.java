/**
 * Title: StreadyDeduction.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������3:11:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:StreadyDeduction2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������3:11:12
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
		//��ȡ���׽��
		double m = trade.getAmount();
		//��ȡIC���еĹ̶����
		double s = card.getSteadyMoney();
		//��ȡIC���е����ɽ��
		double f = card.getFreeMoney();
		if(m>s+f){
			return false;
		}else{
			if(s>m){
				card.setSteadyMoney(s-m);
				return true;
			}else{
				//�ӹ̶�����п۳���Ȼ������ɽ���п۳�
				card.setSteadyMoney(0);
				card.setFreeMoney(f+s-m);
				return true;
			}
		}
	}

}
