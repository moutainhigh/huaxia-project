package ThinkInJava;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;
/**
 * 字符串函数的使用
 * @author liuwei
 */
public class SplitDemo {
	public static void main(String args[]){
		String input = "This!!unusual use!!of exclamation!!points";
		System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
		System.out.println(Arrays.toString(Pattern.compile("!!").split(input,3)));
		
	}
}
