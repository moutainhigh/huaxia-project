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
 * @param: 
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������3:42:52
 */
public class Text {

	/**
	 * Constructor
	 */
	public Text() {
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
			TextThread t = new TextThread();
			new Thread(t).start();
			new Thread(t).start();
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
class TextThread implements Runnable{
	private int num  = 5;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			synchronized(this){
				if(num > 0){
					try {
						Thread.sleep(500);
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
		}
	}
	
}
