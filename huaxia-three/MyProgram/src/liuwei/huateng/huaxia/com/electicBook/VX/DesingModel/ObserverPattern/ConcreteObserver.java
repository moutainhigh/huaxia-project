/**
 * Title: ConcreteObserver.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午10:43:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern;

/**
 * @class_name:ConcreteObserver2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午10:43:18
 */
public class ConcreteObserver implements Observer {

	/**
	 * 
	 */
	public ConcreteObserver() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ObserverPattern.Observer#upodate()
	 */
	@Override
	public void upodate() {
		// TODO Auto-generated method stub
		System.out.println("收到通知，并进行处理！");
	}

}
