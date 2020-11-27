/**
 * Title: Colleague.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日下午2:35:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

/**
 * @class_name:Colleague2020年1月7日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日下午2:35:52
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
