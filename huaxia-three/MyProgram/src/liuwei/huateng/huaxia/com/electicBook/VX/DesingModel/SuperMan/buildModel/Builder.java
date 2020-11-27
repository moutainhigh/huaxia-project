/**
 * Title: Builder.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午4:49:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:Builder2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午4:49:17
 */
public abstract class Builder {
	protected final SuperMan superMan =new SuperMan();
	
	/**
	 * 
	 */
	public Builder() {
		// TODO Auto-generated constructor stub
	}

	public SuperMan getSuperMan() {
		return superMan;
	}
	//建造身体
	public abstract void setBody();
	//建造能力
	public abstract void setSpecialTalnent();
	//建造标志
	public abstract void setSpecialSymbol();
}
