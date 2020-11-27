/**
 * Title: Runner.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月17日下午2:33:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Runner2020年9月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月17日下午2:33:26
 */
public class Runner extends Thread {
	private int speed;//速度
	private int length;//已经跑的长度
	private Road road;//跑道
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
	 *@Date: 2020年9月17日下午2:35:42
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
	 *@Date: 2020年9月17日下午2:33:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Road road = new Road(100);
		for(int i=0;i<10;i++){
			Runner r =new Runner("运动员"+i,i+1,road);
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
					System.out.println(getName()+"跑了"+length+"米"	);
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
