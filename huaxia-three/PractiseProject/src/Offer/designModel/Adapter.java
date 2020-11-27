/**
 * Title: Adapter.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��29������2:43:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Adapter2020��9��29��
 * @comments:
 * @param:������ģʽ
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��29������2:43:59
 */
public class Adapter extends Source implements Targetable {
	
	/**
	 * Constructor
	 */
	public Adapter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Targetable#editWordFile()
	 */
	@Override
	public void editWordFile() {
		// TODO Auto-generated method stub
		System.out.println("a word file editing");
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��29������2:44:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Targetable target = new Adapter();
		target.editTextFile();
		target.editWordFile();
	}

}
