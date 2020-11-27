/**
 * Title: StrategyMan.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������3:25:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:StrategyMan2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������3:25:55
 */
public enum StrategyMan {
	SteadyDeduction("liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy.StreadyDeduction"),
	FreeDeduction("liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy.FreeDeduction");
	private String value;
	private StrategyMan(String val)
	{
		this.value = val;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
