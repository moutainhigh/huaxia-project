/**
 * Title: X201.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午10:47:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:X2012020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午10:47:08
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
