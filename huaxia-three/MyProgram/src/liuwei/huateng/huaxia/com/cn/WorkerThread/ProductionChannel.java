/**
 * Title: ProductionChannel.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午4:26:15
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

/**
 * @class_name:ProductionChannel2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午4:26:15
 */
public class ProductionChannel {
	//传动带上最多有多少个待加工的产品
	private  static int MAX_PROD = 10;
	//主要从来存放待加工的产品，也就是传动带
	private  Production[] productionQueue;
	//队列尾
	private int tail ;
	//队列头
	private int head;
	//当前流水线上有多少个待加工的产品
	private int total;
	//在流水线撒花姑娘工作的工人
	private  Worker[] workers;
	/**
	 * 
	 */
	public ProductionChannel(int workerSize) {
		// TODO Auto-generated constructor stub
		this.workers = new Worker[workerSize];
		this.productionQueue = new Production[MAX_PROD];
		//实例化每一个工作
		for(int i=0;i<workerSize;i++){
			workers[i] = new Worker("Worker-"+i,this);
			workers[i].start();
		}
	}
	//接受来自上游的半成品（待加工的产品）
	public void offerProduction(Production pruduction){
		synchronized(this){
			while(total>=productionQueue.length){
				try {
					this.wait();
					System.err.println("生产停止");
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
	//工人线程（Worker）从传送带上获取产品，并且进行加工
	public Production takeProduction(){
		synchronized(this){
			//当传送带上没有产品时候，工人等待着产品从上游运下来
			while(total<=0){
				try {
					this.wait();
					System.err.println("获取停止");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//获取产品
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
	 *@Date: 2019年12月16日下午4:26:15
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
