package ThinkInJava;
import java.util.Random;
/**
 * 测试字符串的使用
 * @author liuwei
 */
public class UsingStringBuilder {
	public static Random rand = new Random(47);
	public String toString(){
		StringBuilder result = new StringBuilder("[");
		for(int i=0;i<25;i++){
			result.append(rand.nextInt(100));
			result.append(",");
		}
		result.delete(result.length()-1, result.length());
		result.append("]");
		return result.toString();
	}
	public static void main(String args[]){
		UsingStringBuilder usb = new UsingStringBuilder();
		System.out.println(usb.toString());
	}
}
