/**
 * Title: DeadLock.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��9������2:04:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

/**
 * @class_name:DeadLock2019��12��9��
 * @comments:��ɵ���������
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��9������2:04:48
 */
public class DeadLock {
	private final Object MUTEX_READ  = new Object();
	private final Object MUTEX_WRITE  = new Object();
	/**
	 * @Title: read
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��10������4:34:29
	 */
	public void read(){
		synchronized(MUTEX_READ){
			System.out.println(Thread.currentThread().getName()+" get READ lock");
			synchronized(MUTEX_WRITE){
				System.out.println(Thread.currentThread().getName()+" get WRITE lock");
			}
			System.out.println(Thread.currentThread().getName()+" release WRITE lock");
		}
		System.out.println(Thread.currentThread().getName()+" release READ lock");
	}
	/**
	 * @Title: write
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��10������4:35:09
	 */
	public void write(){
		synchronized(MUTEX_WRITE){
			System.out.println(Thread.currentThread().getName()+" get WRITE lock");
			synchronized(MUTEX_READ){
				System.out.println(Thread.currentThread().getName()+" get READ lock");
			}
			System.out.println(Thread.currentThread().getName()+" release READ lock");
		}
		System.out.println(Thread.currentThread().getName()+" release WRITE lock");
	}
	/**
	 * 
	 */
	public DeadLock() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��9������2:04:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final DeadLock deadLock = new DeadLock();
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					deadLock.read();
				}
			}
		},"READ-THREAD").start();
		new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					deadLock.write();
				}
			}
		},"WRITE-THREAD").start();
	}

}
