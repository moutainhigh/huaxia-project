/**
 * Title: AA.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������3:30:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:AA2020��9��22��
 * @comments:
 * @param: ���̵߳�����ʵ��
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������3:30:04
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
	 *@Date: 2020��9��22������3:30:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = Thread.currentThread();
		t.setName("���߳�");
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
