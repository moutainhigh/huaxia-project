/**
 * Title: ComputerDetail.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日下午2:10:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ComputerDetail2020年9月29日
 * @comments:深度复制，引用对象一起复制
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日下午2:10:50
 */
public class ComputerDetail implements Cloneable {
	private String cpu;
	private String memory;
	private Disk disk;
	/**
	 * Constructor
	 */
	public ComputerDetail() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public ComputerDetail(String cpu,String memory,Disk disk) {
		// TODO Auto-generated constructor stub
		this.cpu = cpu;
		this.memory = memory;
		this.disk = disk;
	}
	public String getCpu() {
		return cpu;
	}
	public String getMemory() {
		return memory;
	}
	public Disk getDisk() {
		return disk;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public void setDisk(Disk disk) {
		this.disk = disk;
	}
	@Override
	protected Object clone() {
		// TODO Auto-generated method stub
		try {
			ComputerDetail computerDetail = (ComputerDetail)super.clone();
			computerDetail.disk = (Disk) this.disk.clone();
			return computerDetail;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public String toString() {
		return "ComputerDetail [cpu=" + cpu + ", memory=" + memory + ", disk=" + disk + "]";
	}
	
}
