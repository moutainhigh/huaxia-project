/**
 * Title: Adapter.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日下午2:43:59
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Adapter2020年9月29日
 * @comments:
 * @param:适配器模式
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日下午2:43:59
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
	 *@Date: 2020年9月29日下午2:44:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Targetable target = new Adapter();
		target.editTextFile();
		target.editWordFile();
	}

}
