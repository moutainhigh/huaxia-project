/**
 * Title: StrategyMan.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午3:25:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacytoryStrategy;

/**
 * @class_name:StrategyMan2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午3:25:55
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
