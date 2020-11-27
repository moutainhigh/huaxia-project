package UnderstandingTheJVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 不断的创建对象，导致堆区的内存溢出
 * @author liuwei
 *
 */
public class HeapOOM {
	static class OOMObject{
	}
	public static void main(String args[]){
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}
}
