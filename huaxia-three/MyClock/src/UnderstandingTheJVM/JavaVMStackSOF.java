package UnderstandingTheJVM;
/**
 * 递归调用，测试栈的深度，导致栈的溢出
 * 栈溢出。
 * stack length： 11422
 * @author liuwei
 *
 */
public class JavaVMStackSOF {
	private int stackLength = 1;
	/**
	 * @Title: stackLeak
	 *@Description: 递归，自己调用自己，无出口条件
	 *@author: LiuWei
	 *@Date: 2019年7月22日下午12:50:04
	 */
	public void stackLeak(){
		stackLength ++;
		stackLeak();
	}
	public static void main(String args[]) throws Throwable{
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try{
			oom.stackLeak();
		}catch(Throwable e){
			System.out.println("stack length： "+oom.stackLength);
//			e.printStackTrace();
			throw e;
		}
	}
	
}
