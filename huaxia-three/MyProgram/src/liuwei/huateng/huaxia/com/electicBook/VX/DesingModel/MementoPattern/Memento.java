/**
 * Title: Memento.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������11:20:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.MementoPattern;

/**
 * @class_name:Memento2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������11:20:36
 */
public class Memento {
	//�����˵��ڲ�״̬
	private String state;
	/**
	 * 
	 */
	public Memento(String state) {
		// TODO Auto-generated constructor stub
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
