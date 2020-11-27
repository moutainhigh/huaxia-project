/**
 * Title: SingleDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��3������3:53:09
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;
/**
 * @class_name:SingleDemo2020��1��3��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��3������3:53:09
 */
public class SingleDemo2 {
	/**
	 * 
	 */
	public SingleDemo2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��3������3:53:09
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumThread threadA = new NumThread("�߳�A") ;
		NumThread threadB = new NumThread("�߳�B") ;
		threadA.start();
		threadB.start();
	}

}
/**
 * 
 * @class_name:NumThread2020��1��3��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��3������4:01:08
 */
class NumThread2 extends Thread
{
	private String threadName;
	public NumThread2(String name){
		this.threadName = name;
	}
	@Override
	public void run()
	{
		GlobalNum2 gnObj = GlobalNum2.getInstance();
		for(int i=0;i<5;i++)
		{
			System.out.println(threadName+"��"+gnObj.getNum()+"�η���!");
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
