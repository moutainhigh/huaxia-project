/**
 * Title: T410Builder.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������11:01:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:T410Builder2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������11:01:15
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
		computer.setHardDisk("250GB 5400ת");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildGraphicCard()
	 */
	@Override
	public void buildGraphicCard() {
		// TODO Auto-generated method stub
		//���Կ�
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildMonitor()
	 */
	@Override
	public void buildMonitor() {
		// TODO Auto-generated method stub
		computer.setMonitor("12Ӣ�� 1280*800");
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern.ComputerBuilder#buildOs()
	 */
	@Override
	public void buildOs() {
		// TODO Auto-generated method stub
		computer.setOs("Windows 7 Home��");
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
