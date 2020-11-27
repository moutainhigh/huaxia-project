/**
 * Title: AdultSuperMan.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午4:36:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.abstractFactory;

/**
 * @class_name:AdultSuperMan2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午4:36:14
 */
public class AdultSuperMan implements ISuperMan {

	/**
	 * 
	 */
	public AdultSuperMan() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.ISuperMan#specicalTalent()
	 */
	@Override
	public void specicalTalent() {
		// TODO Auto-generated method stub
		System.out.println("成年超人力大无穷，可以飞行!");
	}

}
