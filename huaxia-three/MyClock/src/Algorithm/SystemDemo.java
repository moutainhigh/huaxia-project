package Algorithm;
/**
 * 计算程序的执行时间
 * @author Liuwei
 */
public class SystemDemo {
	public static void main(String args[]){
		try{
			long start = System.currentTimeMillis();
			System.out.println("开始执行时间为"+start);
			Thread.sleep(3000);
			long end = System.currentTimeMillis();
			System.out.println("结束执行时间为"+end);
			System.out.println("共执行了"+(end-start)+"毫秒");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
