/**
 * Title: FlightSecurityTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��13������9:34:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.SingleThread;

/**
 * @class_name:FlightSecurityTest2019��12��13��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��13������9:34:44
 */
public class FlightSecurityTest {

	static class Passengers extends Thread{
		//����������
		private  FlightSecurity2 flightSecurity;
		//�ÿ����֤
		private String idCard ;
		//�ÿ͵ǻ���
		private String boardingPass;
		/**
		 * ����������
		 * @param flightSecurity
		 * @param idCard
		 * @param boardingPass
		 */
		public Passengers(FlightSecurity2 flightSecurity,String idCard,String boardingPass){
			this.flightSecurity = flightSecurity;
			this.idCard  = idCard;
			this.boardingPass = boardingPass;
		}
		@Override
		public void run(){
//			while(true){
				flightSecurity.pass(boardingPass, idCard);
//			}
		}
	}
	/**
	 * 
	 */
	public FlightSecurityTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��13������9:34:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final FlightSecurity2 flightSecurity = new FlightSecurity2();
		new Passengers(flightSecurity,"A123456","AF123456").start();
		new Passengers(flightSecurity,"A123456","BF23456").start();
		new Passengers(flightSecurity,"C123456","CF123456").start();
		new Passengers(flightSecurity,"A123456","AF123456").start();
		new Passengers(flightSecurity,"A123456","BF23456").start();
		new Passengers(flightSecurity,"C123456","CF123456").start();
		new Passengers(flightSecurity,"A123456","AF123456").start();
		new Passengers(flightSecurity,"A123456","BF23456").start();
		new Passengers(flightSecurity,"C123456","CF123456").start();
		new Passengers(flightSecurity,"A123456","AF123456").start();
		new Passengers(flightSecurity,"A123456","BF23456").start();
		new Passengers(flightSecurity,"C123456","CF123456").start();
	}

}
