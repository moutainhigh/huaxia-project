package UnderstandingTheJVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用JDK的工具监控堆栈的使用情况
 * @author liuwei
 *
 */
public class JConsole {
	static class OOMObject{
		public byte[] placeholder = new byte[64*1024];
	}
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list = new ArrayList<OOMObject>();
		for(int i=0;i<num;i++){
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		System.gc();
	}
	public static void main(String args[]) throws Exception{
		fillHeap(1000);
	}
}
