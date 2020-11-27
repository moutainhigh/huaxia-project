/**
 * Title: ThreadPool.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��16������3:05:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

/**
 * @class_name:ThreadPool2019��12��16��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��16������3:05:44
 */
public interface ThreadPool {
	//�ύ�����̳߳�
	void execute(Runnable runnable);
	//�ر��̳߳�
	void shutdown();
	//��ȡ�̳߳صĳ�ʼ����С
	int getInitSize();
	//��ȡ�̳߳ص�����߳�����
	int getMaxSize();
	//��ȡ�̳߳صĺ����߳�����
	int getCoreSize();
	//��ȡ�̳߳������ڻ���������еĴ�С
	int getQueueSize();
	//��ȡ�̳߳��л�Ծ�̵߳�����
	int getActiveCount();
	//��ѯ�̳߳��Ƿ��Ѿ����ر�
	boolean isShutdown();
}
