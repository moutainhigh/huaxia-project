package ThinkInJava;
/**
 * 字符串的大小写转换
 * @author User
 *
 */
public class Immutable {
	public static String upcase(String s){
		return s.toUpperCase();
	}
	public static void main(String asrgs[]){
		String q = "howdy";
		System.out.println(q);
		String qq = upcase(q);
		System.out.println(qq);
		System.out.println(q);
	}
}
