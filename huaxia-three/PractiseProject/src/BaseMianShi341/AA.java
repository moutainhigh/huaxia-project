/**
 * Title: AA.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日下午3:30:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:AA2020年9月22日
 * @comments:
 * @param: 单线程的运行实例
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午3:30:04
 */
public class AA {

	/**
	 * Constructor
	 */
	public AA() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午3:30:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = Thread.currentThread();
		t.setName("单线程");
		System.out.println(t.getName()+"is a runing thread!");
		try {
			for(int i=0;i< 5;i++){
				System.out.println("sleep time"+i);
				Thread.sleep(300);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
