/**
 * Title: AdultSuperManBuilder.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午5:12:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:AdultSuperManBuilder2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午5:12:03
 */
public class AdultSuperManBuilder extends Builder {

	/**
	 * 
	 */
	public AdultSuperManBuilder() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel.Builder#setBody()
	 */
	@Override
	public void setBody() {
		// TODO Auto-generated method stub
		superMan.setBody("强壮的身体");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel.Builder#setSpecialTalnent()
	 */
	@Override
	public void setSpecialTalnent() {
		// TODO Auto-generated method stub
		superMan.setSpecialTalent("可以飞行");

	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel.Builder#setSpecialSymbol()
	 */
	@Override
	public void setSpecialSymbol() {
		// TODO Auto-generated method stub
		superMan.setSpecialSymbol("胸前带S标记");
	}
}
