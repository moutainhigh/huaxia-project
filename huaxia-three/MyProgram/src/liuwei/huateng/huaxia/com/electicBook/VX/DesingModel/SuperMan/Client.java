/**
 * Title: Client.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������4:39:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan;

/**
 * @class_name:Client2020��1��8��
 * @comments:����ģʽ���쳬��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������4:39:46
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
	 *@Date: 2020��1��8������4:39:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("============����һ�����곬��=================");
		SuperManFactory superManFactory = new AdultSuperManFactory();
		ISuperMan superMan = superManFactory.createSuperMan();
		superMan.specicalTalent();
		System.out.println("---------------����һ��δ����ĳ���----------------------");
		superManFactory = new ChildSuperManFactory();
		superMan = superManFactory.createSuperMan();
		superMan.specicalTalent();
	}

}
