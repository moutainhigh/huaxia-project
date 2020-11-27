/**
 * Title: Computer.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午10:35:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:Computer2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午10:35:56
 */
public abstract class Computer {
	private String type ;//型号
	private String cpu;//CPU
	private String ram;//内存
	private String hardDisk;//硬盘
	private String monitor;//显示器
	private String os;//操作系统
	/**
	 * 
	 */
	public Computer() {
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public String getCpu() {
		return cpu;
	}
	public String getRam() {
		return ram;
	}
	public String getHardDisk() {
		return hardDisk;
	}
	public String getMonitor() {
		return monitor;
	}
	public String getOs() {
		return os;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
}
