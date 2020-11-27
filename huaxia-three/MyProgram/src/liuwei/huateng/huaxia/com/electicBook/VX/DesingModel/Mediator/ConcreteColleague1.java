/**
 * Title: ConcreteColleague1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日下午2:39:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

/**
 * @class_name:ConcreteColleague12020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日下午2:39:41
 */
public class ConcreteColleague1 extends Colleague {

	/**
	 * @param mediator
	 */
	public ConcreteColleague1(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator.Colleague#action()
	 */
	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("这是同事1的行动方法");
	}

}
