/**
 * Title: ProductionChannel.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��16������4:26:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

/**
 * @class_name:ProductionChannel2019��12��16��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��16������4:26:15
 */
public class ProductionChannel {
	//������������ж��ٸ����ӹ��Ĳ�Ʒ
	private  static int MAX_PROD = 10;
	//��Ҫ������Ŵ��ӹ��Ĳ�Ʒ��Ҳ���Ǵ�����
	private  Production[] productionQueue;
	//����β
	private int tail ;
	//����ͷ
	private int head;
	//��ǰ��ˮ�����ж��ٸ����ӹ��Ĳ�Ʒ
	private int total;
	//����ˮ���������﹤���Ĺ���
	private  Worker[] workers;
	/**
	 * 
	 */
	public ProductionChannel(int workerSize) {
		// TODO Auto-generated constructor stub
		this.workers = new Worker[workerSize];
		this.productionQueue = new Production[MAX_PROD];
		//ʵ����ÿһ������
		for(int i=0;i<workerSize;i++){
			workers[i] = new Worker("Worker-"+i,this);
			workers[i].start();
		}
	}
	//�����������εİ��Ʒ�����ӹ��Ĳ�Ʒ��
	public void offerProduction(Production pruduction){
		synchronized(this){
			while(total>=productionQueue.length){
				try {
					this.wait();
					System.err.println("����ֹͣ");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			productionQueue[tail] = pruduction;
			tail = (tail+1)%productionQueue.length;
			total++;
			this.notifyAll();
			}
	}
	//�����̣߳�Worker���Ӵ��ʹ��ϻ�ȡ��Ʒ�����ҽ��мӹ�
	public Production takeProduction(){
		synchronized(this){
			//�����ʹ���û�в�Ʒʱ�򣬹��˵ȴ��Ų�Ʒ������������
			while(total<=0){
				try {
					this.wait();
					System.err.println("��ȡֹͣ");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//��ȡ��Ʒ
			Production prod = productionQueue[head];
			head = (head+1)%productionQueue.length;
			total --;
			this.notifyAll();
			return prod;
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��16������4:26:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
