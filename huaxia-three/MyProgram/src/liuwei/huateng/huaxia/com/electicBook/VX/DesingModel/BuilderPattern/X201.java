/**
 * Title: X201.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������10:47:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:X2012020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������10:47:08
 */
public class X201 extends Computer {
	
	/**
	 * 
	 */
	public X201() {
		// TODO Auto-generated constructor stub
		this.setType("ThinkPad X201i");
	}

	@Override
	public String toString() {
		return "X201 [getType()=" + getType() + ", getCpu()=" + getCpu() + ", getRam()=" + getRam() + ", getHardDisk()="
				+ getHardDisk() + ", getMonitor()=" + getMonitor() + ", getOs()=" + getOs() + "]";
	}	
	
}
