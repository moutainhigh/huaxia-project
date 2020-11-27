package JavaMultiThreadProgramming.SingleCase;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 序列化和反序列化
 * @author liuwei
 *
 */
public class MyObject8 implements Serializable{
	private static final long serialVersionUID = 1L;
	//内部类方式
	public static class MyObjectHandler{
		private static final MyObject8 myObject = new MyObject8();
	}
	private MyObject8(){
	}
	public static MyObject8 getInstance(){
		return MyObjectHandler.myObject;
	}
	protected Object readResolve() throws ObjectStreamException{
		System.out.println("调用了readResolve方法！");
		return MyObjectHandler.myObject;
	}
}
