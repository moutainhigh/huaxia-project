/**
 * Title: Originator.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������11:19:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.MementoPattern;

/**
 * @class_name:Originator2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������11:19:23
 */
public class Originator {
	//�ڲ�״̬
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
	 *@Description: TODO��������¼
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��8������11:22:06
	 */
	public Memento creteMemento(){
		return new Memento(this.state);
	}
	/**
	 * @Title: restoreMemento
	 *@Description: TODO �ָ�һ������¼
	 *@param memento
	 *@author: LiuWei
	 *@Date: 2020��1��8������11:22:43
	 */
	public void restoreMemento(Memento memento){
		this.setState(memento.getState());
	}
}
