/**
 * Title: InstructionBook.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��16������4:08:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

/**
 * @class_name:InstructionBook2019��12��16��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��16������4:08:52
 */
public abstract class InstructionBook {

	public final void create(){
		this.firstProcess();
		this.secondProcess();
	}
	protected abstract void firstProcess();
	protected abstract void secondProcess();
}
