/**
 * Title: CountDownLatch.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��12������2:46:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Latch;

/**
 * @class_name:CountDownLatch2019��12��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��12������2:46:42
 */
public class CountDownLatch extends Latch {

	/**
	 * @param limit
	 */
	public CountDownLatch(int limit) {
		super(limit);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.cn.Latch.Latch#await()
	 */
	@Override
	public void await() throws InterruptedException {
		// TODO Auto-generated method stub
		synchronized(this){
			while(limit > 0)
			{
				this.wait();
			}
		}
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.cn.Latch.Latch#countDown()
	 */
	@Override
	public void countDown() {
		// TODO Auto-generated method stub
		synchronized(this){
			if(limit < 0)
				throw new IllegalStateException("all of task already arrived");
			limit--;
			System.out.println("����"+limit+"�˵��Ｏ�ϵص�");
			this.notifyAll();
		}
	}

	/* (non-Javadoc)
	 * @see liuwei.huateng.huaxia.com.cn.Latch.Latch#getUnarrived()
	 */
	@Override
	public int getUnarrived() {
		// TODO Auto-generated method stub
		return limit;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��12������2:46:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final CountDownLatch latch = new CountDownLatch(5);
		new Thread(new Runnable(){
			@Override
			public void run(){
				try {
					latch.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		for(int i=0;i<10;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
						latch.countDown();
				}
			}).start();
		}
	}
}
