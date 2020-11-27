/**
 * Title: TryConcurrency.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午2:50:30
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:TryConcurrency2019年12月5日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午2:50:30
 */
public class TryConcurrency {
	/**
	 * 
	 */
	public TryConcurrency() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午2:50:30
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		browseNews();
		enjoyMusic();
	}
	/**
	 * @Title: browseNews
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午2:52:24
	 */
	private static void browseNews(){
		for(;;){
			System.out.println("Uh-huh,the good news.");
			sleep(1);
		}
	}
	private static void enjoyMusic(){
		for(;;){
			System.out.println("Uh-huh,the nice music.");
			sleep(1);
		}
	}
	/**
	 * @Title: sleep
	 *@Description: TODO
	 *@param seconds
	 *@author: LiuWei
	 *@Date: 2019年12月5日下午2:52:20
	 */
	private static  void sleep(int seconds){
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
