/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午11:23:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.MementoPattern;

/**
 * @class_name:Client2020年1月8日
 * @comments:备忘录模式
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午11:23:54
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
	 *@Date: 2020年1月8日上午11:23:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Originator  org= new Originator();
		Caretaker car = new Caretaker();
		car.setMemento(org.creteMemento());
		org.restoreMemento(car.getMemento());
		
	}

}
