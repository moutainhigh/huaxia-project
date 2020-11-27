/**
 * Title: PhoneFactory2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��28������4:52:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:PhoneFactory22020��9��28��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��28������4:52:05
 */
public class PhoneFactory2 extends AbstractFactory {

	/**
	 * Constructor
	 */
	public PhoneFactory2() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.AbstractFactory#createPhone(java.lang.String)
	 */
	@Override
	public Phone2 createPhone(String brand) {
		// TODO Auto-generated method stub
		if("HuaWei".equals(brand)){
			return new PhoneHuaWei();
		}else if("Apple".equals(brand)){
			return new PhoneApple();
		}else
		return null;
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.AbstractFactory#createComputer(java.lang.String)
	 */
	@Override
	public Computer createComputer(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

}
