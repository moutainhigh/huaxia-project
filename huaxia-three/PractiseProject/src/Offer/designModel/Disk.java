/**
 * Title: Disk.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日下午2:11:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Disk2020年9月29日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日下午2:11:19
 */
public class Disk implements Cloneable {
	private String ssd;
	private String hhd;
	/**
	 * Constructor
	 */
	public Disk() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public Disk(String ssd,String hhd) {
		// TODO Auto-generated constructor stub
		this.ssd = ssd;
		this.hhd = hhd;
	}
	public String getSsd() {
		return ssd;
	}
	public String getHhd() {
		return hhd;
	}
	public void setSsd(String ssd) {
		this.ssd = ssd;
	}
	public void setHhd(String hhd) {
		this.hhd = hhd;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public String toString() {
		return "Disk [ssd=" + ssd + ", hhd=" + hhd + "]";
	}

}
