/**
 * Title: HungrySingleton.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��29������9:32:48
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:HungrySingleton2020��9��29��
 * @comments:����ģʽ����ȡ�������ͳ�ʼ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��29������9:32:48
 */
public class HungrySingleton {
	private static HungrySingleton instance = new HungrySingleton();
	/**
	 * Constructor
	 */
	public HungrySingleton() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: getInstance
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��29������9:33:47
	 */
	public static HungrySingleton getInstance(){
		return instance;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��29������9:32:48
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(HungrySingleton.getInstance().hashCode());
				}
				
			}).start();;
		}
	}

}
