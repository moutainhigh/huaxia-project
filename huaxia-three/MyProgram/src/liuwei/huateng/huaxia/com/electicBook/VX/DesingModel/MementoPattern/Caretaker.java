/**
 * Title: Caretaker.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午11:23:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.MementoPattern;

/**
 * @class_name:Caretaker2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午11:23:24
 */
public class Caretaker {
	private Memento memento;
	/**
	 * 
	 */
	public Caretaker() {
		// TODO Auto-generated constructor stub
	}
	public Memento getMemento() {
		return memento;
	}
	public void setMemento(Memento memento) {
		this.memento = memento;
	}

}
