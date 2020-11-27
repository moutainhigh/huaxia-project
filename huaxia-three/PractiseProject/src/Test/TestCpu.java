/**
 * Title: TestCpu.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月23日下午12:31:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Test;

/**
 * @class_name:TestCpu2020年10月23日
 * @comments:测试cpu的使用，调用sleep方法调节cpu
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月23日下午12:31:31
 */
public class TestCpu {

	/**
	 * Constructor
	 */
	public TestCpu() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月23日下午12:31:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long  n = 8000000;
		int i=0;
		while(true){
			for( i=0;i<n;i++){
				System.out.println("Hello World`");
				if(i%10 == 0){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			i=0;
		}
	}

}
