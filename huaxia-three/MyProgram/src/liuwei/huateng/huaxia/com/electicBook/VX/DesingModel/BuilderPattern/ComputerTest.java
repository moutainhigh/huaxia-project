/**
 * Title: ComputerTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午11:20:34
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:ComputerTest2020年1月6日
 * @comments:建造者模式，23种设计模式之一
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午11:20:34
 */
public class ComputerTest {

	/**
	 * 
	 */
	public ComputerTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午11:20:34
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComputerDirector director = new ComputerDirector();
		Computer t410 = director.constructT410();
		System.out.println(t410);
		System.out.println("------------------------------------");
		Computer x201= director.constructX201();
		System.out.println(x201);
	}

}
