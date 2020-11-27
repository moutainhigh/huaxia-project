/**
 * Title: Client.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午5:18:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.buildModel;

/**
 * @class_name:Client2020年1月8日
 * @comments:建造者模式创建超人
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午5:18:22
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
	 *@Date: 2020年1月8日下午5:18:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Director director =new Director();
		SuperMan adultSuperMan = director.buildSuperMan("adult");
		System.out.println("成年超人："+adultSuperMan);
		SuperMan childSuperMan = director.buildSuperMan("child");
		System.out.println("未成年超人："+childSuperMan);
	}

}
