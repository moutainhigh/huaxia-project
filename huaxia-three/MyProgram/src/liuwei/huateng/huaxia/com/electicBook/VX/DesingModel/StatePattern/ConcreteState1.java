/**
 * Title: ConcreteState1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午2:13:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:ConcreteState12020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午2:13:21
 */
public class ConcreteState1 extends State {

	/**
	 * 
	 */
	public ConcreteState1() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern.State#handle()
	 */
	@Override
	public void handle() {
		// TODO Auto-generated method stub
		System.out.println("行为1的处理逻辑");
	}

	@Override
	public String toString() {
		return "ConcreteState1 [context=" + context + "]";
	}
	

}
