package JavaMultiThreadProgramming.ThreadState.ThreadGroup;

import java.text.SimpleDateFormat;
/**
 * SimpleDateFormat是线程不安全的，不能再多线程中使用同一个SimpleDateFormat对象。造成问题。
 * @author liwuei
 *
 */
public class Test3 {
	public static void main(String args[]){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateStringArray = new String[]{
			"2000-01-01","2000-01-02","2000-01-03","2000-01-04",	
			"2000-01-05","2000-01-06","2000-01-07","2000-01-08",	
			"2000-01-09","2000-01-10"	
		};
		MyThread3[] threadArray = new MyThread3[10];
		for(int i=0;i<10;i++){
			threadArray[i] = new MyThread3(sdf,dateStringArray[i]);
		}
		for(int i=0;i<10;i++){
			threadArray[i].start();
		}
	}
}
