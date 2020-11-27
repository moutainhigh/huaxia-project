/*package UnderstandingTheJVM;
import java.lang.reflect.Field;

import sun.misc.Unsafe;
*//**
 * unsafe分配本机内存
 * 自己申请分配内存
 * 内存溢出
 * Exception in thread "main" java.lang.OutOfMemoryError
	at sun.misc.Unsafe.allocateMemory(Native Method)
	at UnderstandingTheJVM.DirectMemoryOOM.main(DirectMemoryOOM.java:19)
 * @author liuwei
 *
 *//*
public class DirectMemoryOOM {
	private static final int _1MB = 1024*1024;
	public static void main(String args[]){
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe;
		try {
			unsafe = (Unsafe)unsafeField.get(null);
			while(true){
				unsafe.allocateMemory(_1MB);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/