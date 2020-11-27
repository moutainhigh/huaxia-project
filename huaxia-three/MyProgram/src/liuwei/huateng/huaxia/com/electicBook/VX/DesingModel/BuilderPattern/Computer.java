/**
 * Title: Computer.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������10:35:56
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:Computer2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������10:35:56
 */
public abstract class Computer {
	private String type ;//�ͺ�
	private String cpu;//CPU
	private String ram;//�ڴ�
	private String hardDisk;//Ӳ��
	private String monitor;//��ʾ��
	private String os;//����ϵͳ
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
