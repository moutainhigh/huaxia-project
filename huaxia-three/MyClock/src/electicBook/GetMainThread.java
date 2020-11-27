package electicBook;
/**
 * 测试线程的一些方法
 * @author Liuwei
 */
public class GetMainThread {
	public static void main(String args[]){
		Thread thread = Thread.currentThread();
		System.out.println("<--当前主线程的ID是"+thread.getId()+"-->");
		System.out.println("<--当前主线程的名称是"+thread.getName()+"-->");
		System.out.println("<--当前主线程的优先级是"+thread.getPriority()+"-->");
		System.out.println("<--当前主线程所在的线程组是"+thread.getThreadGroup().getName()+"-->");
	}
}
