/**
 * Title: Runner.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��17������2:33:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Runner2020��9��17��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��17������2:33:26
 */
public class Runner extends Thread {
	private int speed;//�ٶ�
	private int length;//�Ѿ��ܵĳ���
	private Road road;//�ܵ�
	/**
	 * Constructor
	 */
	public Runner(String name,int speed,Road road) {
		// TODO Auto-generated constructor stub
		super(name);
		this.speed = speed;
		this.road = road;
	}
	/**
	 * @Title: sleep
	 *@Description: TODO
	 *@param time
	 *@author: LiuWei
	 *@Date: 2020��9��17������2:35:42
	 */
	public void sleep(int time ){
		try {
			super.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param arg0Constructor
	 */
	public Runner(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0Constructor
	 */
	public Runner(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public Runner(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public Runner(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public Runner(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2Constructor
	 */
	public Runner(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3Constructor
	 */
	public Runner(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��17������2:33:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Road road = new Road(100);
		for(int i=0;i<10;i++){
			Runner r =new Runner("�˶�Ա"+i,i+1,road);
			r.start();
		}
	}
	public void run(){
		while(true){
			synchronized(road){
				if(road.getWinner() != null){
					break;
				}
				if(length<road.getDistance()){
					length+= speed;
					System.out.println(getName()+"����"+length+"��"	);
					if(length>= road.getDistance()){
						road.setWinner(this);
						break;
					}
					sleep(500);
				}
			}
		}
	}
}	
