package ThinkInJava;
import java.util.Arrays;
/**
 * 分割字符串，根据正则表达式
 * @author liuwei
 */
public class Splitting {
	public static String knights = "Then, When you hava found the shrubbery, you must cut down the mightiest tree in the forest.. with... a herring!";
	public static void main(String args[]){
		split(" ");
		split("\\W+");
		split("n\\W+");
	}
	public static void split(String regex){
		System.out.println(Arrays.toString(knights.split(regex)));
	}
}                                
