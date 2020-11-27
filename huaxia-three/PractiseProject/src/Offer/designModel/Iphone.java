/**
 * Title: Iphone.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月28日下午4:11:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Iphone2020年9月28日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月28日下午4:11:17
 */
public class Iphone implements Phone {

	/**
	 * Constructor
	 */
	public Iphone() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Phone#brand()
	 */
	@Override
	public String brand() {
		// TODO Auto-generated method stub
		return "this is a Apple phone";
	}

}
