/**
 * Title: T410Builder.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午11:01:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:T410Builder2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午11:01:15
 */
public class X201Builder implements ComputerBuilder {
	private X201 computer = new X201();
	/**
	 * 
	 */
	public X201Builder() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildCpu()
	 */
	@Override
	public void buildCpu() {
		// TODO Auto-generated method stub
		computer.setCpu("i3-350");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildRam()
	 */
	@Override
	public void buildRam() {
		// TODO Auto-generated method stub
		computer.setRam("2GB 1333MHZ");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildHardDisk()
	 */
	@Override
	public void buildHardDisk() {
		// TODO Auto-generated method stub
		computer.setHardDisk("250GB 5400转");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildGraphicCard()
	 */
	@Override
	public void buildGraphicCard() {
		// TODO Auto-generated method stub
		//无显卡
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildMonitor()
	 */
	@Override
	public void buildMonitor() {
		// TODO Auto-generated method stub
		computer.setMonitor("12英寸 1280*800");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildOs()
	 */
	@Override
	public void buildOs() {
		// TODO Auto-generated method stub
		computer.setOs("Windows 7 Home版");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#getResult()
	 */
	@Override
	public Computer getResult() {
		// TODO Auto-generated method stub
		return computer;
	}

}
