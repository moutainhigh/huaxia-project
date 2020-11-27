/**
 * Title: RunnableQueue.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��16������3:17:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

/**
 * @class_name:RunnableQueue2019��12��16��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��16������3:17:07
 */
public interface RunnableQueue {
	//�����µ��������ʱ�����Ƚ���offer������
	void offer(Runnable runnable);
	//�����߳�ͨ��take������ȡRunnable
	Runnable take();
	//��ȡ������������������
	int size();
}
