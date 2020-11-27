/**
 * Title: Builder.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������4:49:17
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:Builder2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������4:49:17
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
	//��������
	public abstract void setBody();
	//��������
	public abstract void setSpecialTalnent();
	//�����־
	public abstract void setSpecialSymbol();
}
