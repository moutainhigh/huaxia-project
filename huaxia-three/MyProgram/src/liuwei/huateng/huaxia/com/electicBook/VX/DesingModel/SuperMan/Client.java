/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午4:39:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan;

/**
 * @class_name:Client2020年1月8日
 * @comments:工厂模式创造超人
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午4:39:46
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
	 *@Date: 2020年1月8日下午4:39:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("============创建一个成年超人=================");
		SuperManFactory superManFactory = new AdultSuperManFactory();
		ISuperMan superMan = superManFactory.createSuperMan();
		superMan.specicalTalent();
		System.out.println("---------------创建一个未成年的超人----------------------");
		superManFactory = new ChildSuperManFactory();
		superMan = superManFactory.createSuperMan();
		superMan.specicalTalent();
	}

}
