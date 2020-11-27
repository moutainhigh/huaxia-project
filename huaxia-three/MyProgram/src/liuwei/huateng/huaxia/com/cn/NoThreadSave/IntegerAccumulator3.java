/**
 * Title: IntegerAccumulator.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��13������3:34:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.NoThreadSave;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:IntegerAccumulator2019��12��13��
 * @comments:���Զ��̵߳�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��13������3:34:19
 */
public class IntegerAccumulator3 {
	private int init;
	/**
	 * 
	 */
	public IntegerAccumulator3(int init) {
		// TODO Auto-generated constructor stub
		this.init = init;
	}
	/**
	 *new Constants���췽��
	 * @param accumulator
	 * @param init
	 */
	public IntegerAccumulator3(IntegerAccumulator3 accumulator,int init){
		this.init = accumulator.getValue()+init;
	}
	//�Գ�ʼֵ��1
	public IntegerAccumulator3 add(int i){
		
		return new IntegerAccumulator3(this,i);
	}
	/**
	 * @Title: getValue
	 *@Description: TODO
	 *@return ���ص�ǰֵ
	 *@author: LiuWei
	 *@Date: 2019��12��13������3:36:12
	 */
	public int getValue()
	{
		return this.init;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��13������3:34:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final IntegerAccumulator3 accumulator = new IntegerAccumulator3(0);
		for(int i=0;i<3;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					int inc =0;
					while(true){
						int oldValue;
						int result ;
						IntegerAccumulator3 accumulator2 =accumulator.add(inc);
							 oldValue = accumulator2.getValue();
							 result = accumulator2.getValue();
					
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
	 * @Title: slowl
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��13������3:46:04
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
