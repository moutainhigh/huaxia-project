/**
 * Title: State.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������2:11:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:State2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������2:11:21
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
	//������Ϊ
	public abstract void handle();

	@Override
	public String toString() {
		return "State [context=" + context + "]";
	}
	
}
