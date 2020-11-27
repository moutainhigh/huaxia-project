package PractisePackage;
/**
 * @class_name:RunFinalize2020年8月6日
 * @comments:测试finalize方法，对象被回收之后进行执行的方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日上午10:31:33
 */
public class RunFinalize {
	protected void finalize() throws Throwable{
		System.out.println("运行finalize方法");
		super.finalize();
	}
	public RunFinalize() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunFinalize runFinalize = new RunFinalize();
		runFinalize = null;
		for(int i=0;i<10;i++){
			System.gc();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
