/**
 * Title: Road.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��17������2:29:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Road2020��9��17��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��17������2:29:54
 */
public class Road extends Thread {
	private int distance;//·��
	private Runner winner;//��һ���ﵽ�յ���˶�Ա
	/**
	 * Constructor
	 */
	public Road() {
		// TODO Auto-generated constructor stub
	}
	public Road(int distance) {
		// TODO Auto-generated constructor stub
		this.distance = distance;
	}
	/**
	 * @param arg0Constructor
	 */
	public Road(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0Constructor
	 */
	public Road(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public Road(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public Road(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public Road(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2Constructor
	 */
	public Road(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3Constructor
	 */
	public Road(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��17������2:29:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Runner getWinner() {
		return winner;
	}
/**
 * @Title: setWinner
 *@Description: TODO
 *@param winner
 *@author: LiuWei
 *@Date: 2020��9��17������2:39:03
 */
	public void setWinner(Runner winner) {
		this.winner = winner;
		System.out.println(winner.getName()+"��һ���ﵽ�յ�");
	}

public int getDistance() {
	return distance;
}

public void setDistance(int distance) {
	this.distance = distance;
}

}
