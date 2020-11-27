/**
 * Title: AdultSuperManFactory.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������4:38:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.abstractFactory;

/**
 * @class_name:AdultSuperManFactory2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������4:38:36
 */
public class ChildHeroFactory implements HeroFactory {

	/**
	 * 
	 */
	public ChildHeroFactory() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.SuperManFactory#createSuperMan()
	 */
	@Override
	public ISuperMan createSuperMan() {
		// TODO Auto-generated method stub
		return new ChildSuperMan();
	}

	@Override
	public ISpiderMan createSpiderMan() {
		// TODO Auto-generated method stub
		return new ChildSpiderMan();
	}

}
