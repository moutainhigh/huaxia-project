/**
 * Title: Tableware.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月13日上午10:31:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.SingleThread;

/**
 * @class_name:Tableware2019年12月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月13日上午10:31:29
 */
public class Tableware {
	//餐具名称
	private final String toolName;
	public Tableware(String toolName){
		this.toolName = toolName;
	}
	
	@Override
	public String toString() {
		return "Tableware [toolName=" + toolName + "]";
	}

}
