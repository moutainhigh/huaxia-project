/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午4:39:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.abstractFactory;

/**
 * @class_name:Client2020年1月8日
 * @comments:抽象工厂模式创造超人
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
		HeroFactory adultFactory = new AdultHeroFactory();
		ISuperMan adultSuperMan = adultFactory.createSuperMan();
		ISpiderMan adultSpiderMan = adultFactory.createSpiderMan();
		adultSuperMan.specicalTalent();
		adultSpiderMan.launchSilk();
		System.out.println("-----------------------------------");
		HeroFactory childFactory = new ChildHeroFactory();
		ISuperMan childSuperMan = childFactory.createSuperMan();
		ISpiderMan childSpiderMan = childFactory.createSpiderMan();
		childSuperMan.specicalTalent();
		childSpiderMan.launchSilk();
	}

}
