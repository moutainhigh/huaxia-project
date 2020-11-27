/**
 * Title: ComputerFactory.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��28������4:57:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ComputerFactory2020��9��28��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��28������4:57:05
 */
public class ComputerFactory extends AbstractFactory {

	/**
	 * Constructor
	 */
	public ComputerFactory() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.AbstractFactory#createPhone(java.lang.String)
	 */
	@Override
	public Phone2 createPhone(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.AbstractFactory#createComputer(java.lang.String)
	 */
	@Override
	public Computer createComputer(String brand) {
		// TODO Auto-generated method stub
		if("HuaWei".equals(brand)){
			return new ComputerHuawei();
		}else if("Apple".equals(brand)){
			return new ComputerApple();
		}else
		return null;
	}

}
