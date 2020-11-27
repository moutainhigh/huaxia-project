/**
 * Title: ComputerConcreteBuilder.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日上午10:08:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ComputerConcreteBuilder2020年9月29日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日上午10:08:55
 */
public class ComputerConcreteBuilder implements ComputerBuilder {
	Computer2 computer;
	/**
	 * Constructor
	 */
	public ComputerConcreteBuilder() {
		// TODO Auto-generated constructor stub
		computer = new Computer2();
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.ComputerBuilder#buildcpu()
	 */
	@Override
	public void buildcpu() {
		// TODO Auto-generated method stub
		System.out.println("buildcpu....");
		computer.setCpu("8core");
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.ComputerBuilder#buildemory()
	 */
	@Override
	public void buildemory() {
		// TODO Auto-generated method stub
		System.out.println("buildmemory....");
		computer.setCpu("16G");
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.ComputerBuilder#buildDisk()
	 */
	@Override
	public void buildDisk() {
		// TODO Auto-generated method stub
		System.out.println("buildDisk....");
		computer.setCpu("1TG");
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.ComputerBuilder#buildCommputer()
	 */
	@Override
	public Computer2 buildCommputer() {
		// TODO Auto-generated method stub
		return computer;
	}

}
