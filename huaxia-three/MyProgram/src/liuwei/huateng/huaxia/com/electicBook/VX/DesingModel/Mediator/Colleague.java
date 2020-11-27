/**
 * Title: Colleague.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������2:35:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

/**
 * @class_name:Colleague2020��1��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������2:35:52
 */
public abstract class Colleague {
	private Mediator mediator;
	/**
	 * 
	 */
	public Colleague(Mediator mediator) {
		// TODO Auto-generated constructor stub
		this.mediator = mediator;
	}
	public Mediator getMediator() {
		return mediator;
	}
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}
	public abstract void action();
	public void change(){
		this.mediator.colleagueChanged(this);
	}
}
