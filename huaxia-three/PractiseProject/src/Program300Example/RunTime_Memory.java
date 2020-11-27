/**
 * Title: RunTime_Memory.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日下午3:25:51
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:RunTime_Memory2020年9月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日下午3:25:51
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
	 *@Date: 2020年9月21日下午3:25:51
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Runtime run = Runtime.getRuntime();
			System.out.println("内存可用空间："+run.totalMemory());
			System.out.println("未创建数组时候的剩余空间"+run.freeMemory());
			int base[] = new int[10240];
			Thread.sleep(1000);
			System.out.println("创建数组的时候剩余空间："+run.freeMemory());
			run.gc();
			Thread.sleep(1000);
			System.out.println("启动垃圾回收之后剩余空间："+run.freeMemory());
			Thread.sleep(1000);
			System.out.println("=========为数组分配空间==============");
			for(int i=0;i<10240;i++){
				base[i] = i+1;
			}
			Thread.sleep(2000);
			System.out.println("分配空间之后剩余空间："+run.freeMemory());
			run.gc();
			Thread.sleep(2000);
			System.out.println("启动垃圾回收之后剩余空间："+run.freeMemory());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
