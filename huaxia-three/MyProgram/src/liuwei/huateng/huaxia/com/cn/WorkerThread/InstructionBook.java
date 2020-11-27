/**
 * Title: InstructionBook.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午4:08:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

/**
 * @class_name:InstructionBook2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午4:08:52
 */
public abstract class InstructionBook {

	public final void create(){
		this.firstProcess();
		this.secondProcess();
	}
	protected abstract void firstProcess();
	protected abstract void secondProcess();
}
