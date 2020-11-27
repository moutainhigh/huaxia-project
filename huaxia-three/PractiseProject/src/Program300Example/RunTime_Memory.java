/**
 * Title: RunTime_Memory.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��21������3:25:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:RunTime_Memory2020��9��21��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��21������3:25:51
 */
public class RunTime_Memory {

	/**
	 * Constructor
	 */
	public RunTime_Memory() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��21������3:25:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Runtime run = Runtime.getRuntime();
			System.out.println("�ڴ���ÿռ䣺"+run.totalMemory());
			System.out.println("δ��������ʱ���ʣ��ռ�"+run.freeMemory());
			int base[] = new int[10240];
			Thread.sleep(1000);
			System.out.println("���������ʱ��ʣ��ռ䣺"+run.freeMemory());
			run.gc();
			Thread.sleep(1000);
			System.out.println("������������֮��ʣ��ռ䣺"+run.freeMemory());
			Thread.sleep(1000);
			System.out.println("=========Ϊ�������ռ�==============");
			for(int i=0;i<10240;i++){
				base[i] = i+1;
			}
			Thread.sleep(2000);
			System.out.println("����ռ�֮��ʣ��ռ䣺"+run.freeMemory());
			run.gc();
			Thread.sleep(2000);
			System.out.println("������������֮��ʣ��ռ䣺"+run.freeMemory());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
