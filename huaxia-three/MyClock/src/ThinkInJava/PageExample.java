package ThinkInJava;
import java.nio.CharBuffer;
import java.util.Random;
/**
 * 进行一些生成文章的程序的运行和实现
 * @author Liuwei
 */
public class PageExample {
	private static Random rand = new Random(47);
	private static final char[] capitals = "ABCDEFGHIGKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char[] lowers = "abcdefghigklmnopqrstuvwxyz".toCharArray();
	private static final char[] vowels = "aeiou".toCharArray();
	private int count;
	public PageExample(int count){
		this.count= count;
	}
	public static void main(String args[]){
//		createWord(10);
		createPassage();
	}
	public static String createWord(int lenth){
			StringBuilder str= new StringBuilder();
			str.append(String.valueOf(capitals[rand.nextInt(capitals.length)]));
			for(int i=0;i<lenth;i++){
				str.append(vowels[rand.nextInt(vowels.length)]);
				str.append(lowers[rand.nextInt(lowers.length)]);
			}
//			System.out.println(str);
			return str.toString();
	}
	public static void createPassage(){
		int length = 3000;
		String str= new String();
		int len ;
		for(int i=0;i<length;i++){
			len = rand.nextInt(10)+1;
			str += createWord(len)+" ";
			if(i%20==0)
				str+="\r\n";
		}
		System.out.println(str.toString());
	}
}

