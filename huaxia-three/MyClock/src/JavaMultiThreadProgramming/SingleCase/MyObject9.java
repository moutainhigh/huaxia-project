package JavaMultiThreadProgramming.SingleCase;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * static代码块实现单例模式
 * @author liuwei
 *
 */
public class MyObject9 implements Serializable{
	private static MyObject9 instance = null;
	private MyObject9(){
	}
	static {
		instance = new MyObject9();
	}
	public static MyObject9 getInstance(){
		return instance;
	}
}
