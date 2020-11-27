/**
 * Title: TestDeadLock.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��9������11:16:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

/**
 * @class_name:TestDeadLock2019��12��9��
 * @comments:��������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��9������11:16:29
 */
public class TestDeadLock implements Runnable {
	private final Object mutext_read = new Object();
	private final Object mutext_write = new Object();
	/**
	 * @Title: read
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��9������11:18:05
	 */
	public void read(){
		synchronized(mutext_read){
			synchronized(mutext_write){
				System.out.println("read");
			}
		}
	}
	/**
	 * @Title: write
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��9������11:18:25
	 */
	public void write(){
		synchronized(mutext_write){
			synchronized(mutext_read){
				System.out.println("write");
			}
		}
	}
	/**
	 * 
	 */
	public TestDeadLock() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(Thread.currentThread().getName().equals("read")){
			read();
		}
		if(Thread.currentThread().getName().equals("write")){
			write();
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��9������11:16:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestDeadLock t1 = new TestDeadLock();
		while(true){
			new Thread(t1,"read").start();
			new Thread(t1,"write").start();
		}
	}

}
