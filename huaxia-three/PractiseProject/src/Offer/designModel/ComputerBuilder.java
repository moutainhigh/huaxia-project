/**
 * Title: ComputerBuilder.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日上午10:06:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ComputerBuilder2020年9月29日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日上午10:06:50
 */
public interface ComputerBuilder {
	void buildcpu();
	void buildemory();
	void buildDisk();
	Computer2 buildCommputer();
}
