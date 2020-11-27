/**
 * Title: ArraySort.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��18������9:46:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:ArraySort2020��8��18��
 * @comments: �߳�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��18������9:46:27
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
	 *@Date: 2020��8��18������9:46:27
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
