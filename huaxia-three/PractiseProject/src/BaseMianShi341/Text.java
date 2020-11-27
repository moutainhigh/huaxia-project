/**
 * Title: Text.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日下午3:42:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:Text2020年9月22日
 * @comments:
 * @param: 
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午3:42:52
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
	 *@Date: 2020年9月22日下午3:42:52
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
 * @class_name:TextThread2020年9月22日
 * @comments: 使用synchronize进行线程同步
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午3:46:42
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
						System.out.println(Thread.currentThread().getName()+"出错了");
					}
					System.out.println(Thread.currentThread().getName()+"数字为："+num--);
				}else{
					System.out.println(Thread.currentThread().getName()+"退出了");
					break;
				}
			}
		}
	}
	
}
