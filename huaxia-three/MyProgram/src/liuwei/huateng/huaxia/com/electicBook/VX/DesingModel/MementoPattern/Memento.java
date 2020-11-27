/**
 * Title: Memento.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午11:20:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.MementoPattern;

/**
 * @class_name:Memento2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午11:20:36
 */
public class Memento {
	//发起人的内部状态
	private String state;
	/**
	 * 
	 */
	public Memento(String state) {
		// TODO Auto-generated constructor stub
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
