/**
 * Title: Context.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午2:11:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:Context2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午2:11:46
 */
public class Context {
	public static State STATE1  = new ConcreteState1();
	public static State STATE2  = new ConcreteState2();
	private State currentState;
	/**
	 * 
	 */
	public Context() {
		// TODO Auto-generated constructor stub
	}
	public static State getSTATE1() {
		return STATE1;
	}
	public static State getSTATE2() {
		return STATE2;
	}
	public State getCurrentState() {
		return currentState;
	}
	public static void setSTATE1(State sTATE1) {
		STATE1 = sTATE1;
	}
	public static void setSTATE2(State sTATE2) {
		STATE2 = sTATE2;
	}
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
		currentState.setContext(this);
	}
	@Override
	public String toString() {
		return "Context [currentState=" + currentState + "]";
	}
	/**
	 * @Title: handle1
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午2:18:43
	 */
	public void handle1(){
		//切换状态1
		this.setCurrentState(STATE1);
		this.currentState.handle();
	}
	/**
	 * @Title: handle2
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午2:18:46
	 */
	public void handle2(){
		//切换状态2
		this.setCurrentState(STATE2);
		this.currentState.handle();
	}
}
