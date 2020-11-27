/**
 * Title: State.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午2:11:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:State2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午2:11:21
 */
public abstract class State {
	protected Context context;
	
	/**
	 * 
	 */
	public State() {
		// TODO Auto-generated constructor stub
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	//抽象行为
	public abstract void handle();

	@Override
	public String toString() {
		return "State [context=" + context + "]";
	}
	
}
