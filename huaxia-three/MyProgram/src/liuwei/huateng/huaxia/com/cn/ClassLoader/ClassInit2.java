/**
 * Title: ClassInit2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��12������2:01:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ClassLoader;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:ClassInit22019��12��12��
 * @comments: ��̬�����ֻ���ڼ���ʱ��ִ��һ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��12������2:01:08
 */
public class ClassInit2 {
	static
	{
		try{
			System.out.println("the classInit static code block will be invoke.");
//			TimeUnit.MINUTES.sleep(10);
			TimeUnit.SECONDS.sleep(2);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public ClassInit2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��12������2:01:08
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					new ClassInit2();
				}
			}).start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
