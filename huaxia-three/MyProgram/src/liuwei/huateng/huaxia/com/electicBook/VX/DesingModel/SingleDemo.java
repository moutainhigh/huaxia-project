/**
 * Title: SingleDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月3日下午3:53:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;
/**
 * @class_name:SingleDemo2020年1月3日
 * @comments:恶汉式的测试
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月3日下午3:53:09
 */
public class SingleDemo {
	/**
	 * 
	 */
	public SingleDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月3日下午3:53:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumThread threadA = new NumThread("线程A") ;
		NumThread threadB = new NumThread("线程B") ;
		threadA.start();
		threadB.start();
	}

}
/**
 * 
 * @class_name:NumThread2020年1月3日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月3日下午4:01:08
 */
class NumThread extends Thread
{
	private String threadName;
	public NumThread(String name){
		this.threadName = name;
	}
	@Override
	public void run()
	{
		GlobalNum gnObj = GlobalNum.getInstance();
		for(int i=0;i<5;i++)
		{
			System.out.println(threadName+"第"+gnObj.getNum()+"次访问!");
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
