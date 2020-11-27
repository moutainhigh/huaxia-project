/**
 * Title: Originator.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午11:19:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.MementoPattern;

/**
 * @class_name:Originator2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午11:19:23
 */
public class Originator {
	//内部状态
	private String state = "";
	/**
	 * 
	 */
	public Originator() {
		// TODO Auto-generated constructor stub
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @Title: creteMemento
	 *@Description: TODO创建备忘录
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午11:22:06
	 */
	public Memento creteMemento(){
		return new Memento(this.state);
	}
	/**
	 * @Title: restoreMemento
	 *@Description: TODO 恢复一个备忘录
	 *@param memento
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午11:22:43
	 */
	public void restoreMemento(Memento memento){
		this.setState(memento.getState());
	}
}
