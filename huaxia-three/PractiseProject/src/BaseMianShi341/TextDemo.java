/**
 * Title: TextDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日下午3:33:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:TextDemo2020年9月22日
 * @comments:
 *  run result 名字叫做Thread1  开始休眠2000毫秒
名字叫做Thread2  开始休眠100毫秒
名字叫做Thread3  开始休眠300000毫秒
名字叫做Thread2  开始休眠100毫秒结束
名字叫做Thread1  开始休眠2000毫秒结束
 * @param: 多线程的运行
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午3:33:31
 */
public class TextDemo {

	/**
	 * Constructor
	 */
	public TextDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午3:33:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadSubName t1 = new ThreadSubName("Thread1",2000);
		ThreadSubName t2 = new ThreadSubName("Thread2",100);
		ThreadSubName t3 = new ThreadSubName("Thread3",300000);
		t1.start();
		t2.start();
		t3.start();
	}

}
/**
 * 
 * @class_name:ThreadSubName2020年9月22日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午3:36:06
 */
class ThreadSubName extends Thread{
	private String threadName;
	private int Ms;
	public ThreadSubName(String name,int ms){
		this.threadName =name;
		this.Ms = ms;
	}
	/**
	 * 重新书写run方法
	 */
	@Override
	public void run(){
		System.out.println("名字叫做"+threadName+"  开始休眠"+Ms+"毫秒");
		try {
			sleep(Ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The Thread is Wrong")	;
		}
		System.out.println("名字叫做"+threadName+"  开始休眠"+Ms+"毫秒结束");
	}
}