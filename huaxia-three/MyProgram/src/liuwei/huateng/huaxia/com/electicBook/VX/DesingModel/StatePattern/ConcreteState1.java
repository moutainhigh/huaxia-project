/**
 * Title: ConcreteState1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������2:13:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.StatePattern;

/**
 * @class_name:ConcreteState12020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������2:13:21
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
		System.out.println("��Ϊ1�Ĵ����߼�");
	}

	@Override
	public String toString() {
		return "ConcreteState1 [context=" + context + "]";
	}
	

}
