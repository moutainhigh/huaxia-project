/**
 * Title: Text.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������3:42:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:Text2020��9��22��
 * @comments:
 * @param: �������߳�ͬ��������̻߳�������ݵ��쳣
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������3:42:52
 */
public class Text2 {

	/**
	 * Constructor
	 */
	public Text2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��22������3:42:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextThread2 t1 = new TextThread2();
//		TextThread2 t2 = new TextThread2();

			new Thread(t1).start();
			new Thread(t1).start();
	}
}
/**
 * 
 * @class_name:TextThread2020��9��22��
 * @comments: ʹ��synchronize�����߳�ͬ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������3:46:42
 */
class TextThread2 implements Runnable{
	private int num  = 5;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
//			synchronized(this){
				if(num > 0){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(Thread.currentThread().getName()+"������");
					}
					System.out.println(Thread.currentThread().getName()+"����Ϊ��"+num--);
				}else{
					System.out.println(Thread.currentThread().getName()+"�˳���");
					break;
				}
			}
//		}
	}
}
