/**
 * Title: ArraySort.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日上午9:46:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:ArraySort2020年8月18日
 * @comments: 线程排序法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日上午9:46:27
 */
public class ArraySort implements Runnable {
	private String num;
	/**
	 * Constructor
	 */
	public ArraySort(int num) {
		// TODO Auto-generated constructor stub
		this.num = num + "";
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(Integer.parseInt(num));
			System.out.println(num);
		} catch (NumberFormatException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日上午9:46:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {11,3,998,5455,1,152,990};
		for(int i=0;i<nums.length;i++)
		{
			new Thread(new ArraySort(nums[i])).start();
		}
	}
	
}
