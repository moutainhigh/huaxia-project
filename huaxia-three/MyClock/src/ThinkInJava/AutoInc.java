package ThinkInJava;
/**
 * 测试++i和i++
 * i++是先用，再加
 * ++i 是先加，再用
 * @author liuwei
 *
 */
public class AutoInc {
	public static void main(String args[]){
		int i=1;
		System.out.println("i : "+i);
		System.out.println("++i : "+(++i));
		System.out.println("i++ : "+(i++));
		System.out.println("i : "+i);
		System.out.println("--i : "+(--i));
		System.out.println("i-- : "+(i--));
		System.out.println("i : "+i);
	}
}
