/**
 * Title: Client.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������11:23:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.MementoPattern;

/**
 * @class_name:Client2020��1��8��
 * @comments:����¼ģʽ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������11:23:54
 */
public class Client {
	
	/**
	 * 
	 */
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��8������11:23:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Originator  org= new Originator();
		Caretaker car = new Caretaker();
		car.setMemento(org.creteMemento());
		org.restoreMemento(car.getMemento());
		
	}

}
