/**
 * Title: ComputerDirector.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午11:14:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.BuilderPattern;

/**
 * @class_name:ComputerDirector2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午11:14:31
 */
public class ComputerDirector {
	ComputerBuilder builder;
	/**
	 * 
	 */
	public ComputerDirector() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: constructT410
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午11:18:59
	 */
	public T410 constructT410(){
		builder = new T410Builder();
		builder.buildCpu();
		builder.buildRam();
		builder.buildHardDisk();
		builder.buildGraphicCard();
		builder.buildMonitor();
		builder.buildOs();
		return (T410)builder.getResult();
	}
	/**
	 * @Title: constructX201
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月6日上午11:20:05
	 */
	public X201 constructX201(){
		builder = new X201Builder();
		builder.buildCpu();
		builder.buildRam();
		builder.buildHardDisk();
		builder.buildMonitor();
		builder.buildOs();
		return (X201)builder.getResult();
	}
}
