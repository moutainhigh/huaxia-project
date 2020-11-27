package ThinkInJava;
/**
 * 测试字符串的输出
 * @author liuwei
 */
public class OverloadingOrder {
	static void f(String s,int i){
		System.out.println("String: "+s+",int: "+i);
	}
	static void f(int i,String s){
		System.out.println("int:"+i+",string:"+s);
	}
	public static void main(String args[]){
		f("String first",11);
		f(99,"Int first");
	}
}
