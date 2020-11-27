/**
 * Title: Road.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月17日下午2:29:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Road2020年9月17日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月17日下午2:29:54
 */
public class Road extends Thread {
	private int distance;//路程
	private Runner winner;//第一个达到终点的运动员
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
	 *@Date: 2020年9月17日下午2:29:54
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
 *@Date: 2020年9月17日下午2:39:03
 */
	public void setWinner(Runner winner) {
		this.winner = winner;
		System.out.println(winner.getName()+"第一个达到终点");
	}

public int getDistance() {
	return distance;
}

public void setDistance(int distance) {
	this.distance = distance;
}

}
