/**
 * Title: Latch.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��12������2:41:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Latch;

import java.nio.channels.InterruptedByTimeoutException;

/**
 * @class_name:Latch2019��12��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��12������2:41:39
 */
public abstract class Latch {

	protected int limit;
	public Latch(int limit) {
		// TODO Auto-generated constructor stub
		this.limit = limit;
	}
	//�ȴ��߳�
	public abstract void await() throws InterruptedException;
	//��������һ�ķ���
	public abstract void countDown();
	//��ȡ��ǰ���ж��ٸ��߳�û���������
	public abstract int getUnarrived();
}
