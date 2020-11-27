/**
 * Title: Computer3.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日下午2:07:12
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Computer32020年9月29日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日下午2:07:12
 */
public class Computer3 implements Cloneable {
	private String cpu;
	private String memory;
	private String disk;
	/**
	 * Constructor
	 */
	public Computer3() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public Computer3(String cpu,String memory,String disk) {
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
	public String getDisk() {
		return disk;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public String toString() {
		return "Computer3 [cpu=" + cpu + ", memory=" + memory + ", disk=" + disk + "]";
	}

}
