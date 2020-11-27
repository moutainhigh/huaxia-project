/**
 * Title: Hoop.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月15日下午3:36:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:Hoop2020年9月15日
 * @comments: 一些动态的过程，方法的执行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月15日下午3:36:16
 */
public class Hoop {
	private Trainee trainee;//被调教者
	/**
	 * @Title: hitch
	 *@Description: TODO 套住被调教者
	 *@param trainee
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午3:37:36
	 */
	public void hitch(Trainee trainee){
		this.trainee = trainee;
	}
	/**
	 * Constructor
	 */
	public Hoop() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午3:36:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monkey2 monkey = new Monkey2("孙悟空");
		Bear bear =new Bear("黑熊精");
		Hoop hoopForMonkey = new Hoop();
		hoopForMonkey.hitch(monkey);
		hoopForMonkey.useMagic();
		hoopForMonkey.hitch(bear);
		hoopForMonkey.useMagic();
	}
	/**
	 * @Title: useMagic
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年9月15日下午3:39:22
	 */
	public void useMagic(){
		System.out.println("金箍圈施展法力");
		if(trainee  != null)
			trainee.haveHeadache();
	}
}
