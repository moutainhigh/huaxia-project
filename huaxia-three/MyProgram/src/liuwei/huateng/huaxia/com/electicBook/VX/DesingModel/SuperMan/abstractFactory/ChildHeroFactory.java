/**
 * Title: AdultSuperManFactory.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午4:38:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.abstractFactory;

/**
 * @class_name:AdultSuperManFactory2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午4:38:36
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
