/**
 * Title: TestBuilder.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日上午10:14:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestBuilder2020年9月29日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日上午10:14:50
 */
public class TestBuilder {

	/**
	 * Constructor
	 */
	public TestBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月29日上午10:14:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComputerDirector computerDirector = new ComputerDirector();
		ComputerBuilder computerBuilder = new ComputerConcreteBuilder();
		Computer2 computer = computerDirector.constructComputer(computerBuilder);
		System.out.println(computer.getCpu());
		System.out.println(computer.getDisk());
		System.out.println(computer.getMemory());

	}

}
