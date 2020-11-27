package ThinkInJava;
/**
 * 
 * 测试对象的相等的
 * @author liuwei
 *
 */
public class Equivalence {
	public static void main(String args[]){
		Integer n1 = new Integer(47);
		Integer n2 = new Integer(47);
		System.out.println(n1 == n2);
		System.out.println(n1 != n2);
		System.out.println(n1.equals(n2));
	}
}
