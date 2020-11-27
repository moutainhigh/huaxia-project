/**
 * Title: CllientDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��3������4:52:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:CllientDemo2020��1��3��
 * @comments:����ģʽ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��3������4:52:19
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
	 *@Date: 2020��1��3������4:52:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ƻ��԰������
		FruitGardener fruitGardener = new AppleGardener();
		//ͨ����������ƻ��
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
