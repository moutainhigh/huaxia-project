/**
 * Title: TestCpu.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��23������12:31:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Test;

/**
 * @class_name:TestCpu2020��10��23��
 * @comments:����cpu��ʹ�ã�����sleep��������cpu
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��23������12:31:31
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
	 *@Date: 2020��10��23������12:31:32
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
