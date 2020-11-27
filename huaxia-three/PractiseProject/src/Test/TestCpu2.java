/**
 * Title: TestCpu2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年11月3日上午9:47:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Test;

/**
 * @class_name:TestCpu22020年11月3日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年11月3日上午9:47:16
 */
public class TestCpu2 {

	/**
	 * Constructor
	 */
	public TestCpu2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年11月3日上午9:47:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t =new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i=0;
				while(true)
				{
					i++;
				}
			}
			
		});
		t.start();
		// TODO Auto-generated method stub
				Thread t2 =new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int i=0;
						while(true)
						{
							i++;
						}
					}
					
				});
				t2.start();
				Thread t3 =new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int i=0;
						while(true)
						{
							i++;
						}
					}
					
				});
				t3.start();
				Thread t4 =new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int i=0;
						while(true)
						{
							i++;
						}
					}
					
				});
				t4.start();
	}

}
