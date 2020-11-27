/**
 * Title: ComputerBuilder.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午10:48:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:ComputerBuilder2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午10:48:50
 */
public interface ComputerBuilder {
	 /**
	  * @Title: buildCpu
	  *@Description: TODO
	  *@author: LiuWei
	  *@Date: 2020年1月6日上午10:54:18
	  */
	public void buildCpu();//建造cpu
	/**
	 * @Title: buildRam
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午10:54:24
	 */
	void buildRam();//建造内存
	/**
	 * @Title: buildHardDisk
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午10:54:28
	 */
	void buildHardDisk();//建造硬盘
	/**
	 * @Title: buildGraphicCard
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午10:54:34
	 */
	void buildGraphicCard();//建造显卡
	/**
	 * @Title: buildMonitor
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午10:54:38
	 */
	void buildMonitor();//建造显示器
	/**
	 * @Title: buildOs
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午10:54:42
	 */
	void buildOs();//建造操作系统
	/**
	 * @Title: getResult
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午10:54:46
	 */
	Computer getResult();//得到建造好的计算机
}
