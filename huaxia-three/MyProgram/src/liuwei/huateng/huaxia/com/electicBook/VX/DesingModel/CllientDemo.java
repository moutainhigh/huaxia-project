/**
 * Title: CllientDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月3日下午4:52:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:CllientDemo2020年1月3日
 * @comments:工厂模式生产
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月3日下午4:52:19
 */
public class CllientDemo {

	/**
	 * 
	 */
	public CllientDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月3日下午4:52:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//苹果园丁工厂
		FruitGardener fruitGardener = new AppleGardener();
		//通过工厂生产苹果
		Fruit apple = fruitGardener.factory();
		apple.plant();
		apple.grow();
		apple.harvest();
		
		fruitGardener = new GrapeGardener();
		Fruit grape = fruitGardener.factory();
		grape.plant();
		grape.grow();
		grape.harvest();
	}	

}
