/**
 * Title: IntegerAccumulator.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月13日下午3:34:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.NoThreadSave;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:IntegerAccumulator2019年12月13日
 * @comments:测试多线程的问题
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月13日下午3:34:19
 */
public class IntegerAccumulator {
	private int init;
	/**
	 * 
	 */
	public IntegerAccumulator(int init) {
		// TODO Auto-generated constructor stub
		this.init = init;
	}
	//对初始值加1
	public int add(int i){
		this.init += i;
		return this.init;
	}
	/**
	 * @Title: getValue
	 *@Description: TODO
	 *@return 返回当前值
	 *@author: LiuWei
	 *@Date: 2019年12月13日下午3:36:12
	 */
	public int getValue()
	{
		return this.init;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月13日下午3:34:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final IntegerAccumulator accumulator = new IntegerAccumulator(0);
		for(int i=0;i<3;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					int inc =0;
					while(true){
						int oldValue = accumulator.getValue();
						int result = accumulator.add(inc);
						System.out.println(oldValue+"+"+inc+"="+result);
						if(inc + oldValue != result)
						{
							System.err.println("ERROR:"+oldValue+"+"+inc+"="+result);
						}
						inc++;
						slowly();
					}
				}
			}).start();
			
		}
	}
	/**
	 * @Title: slowly
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年12月13日下午3:46:04
	 */
	private  static  void slowly()
	{
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
