package ThinkInJava;
/**
 * 测试一些char数组的方法
 * @author liuwei
 *
 */
public class ForEachString {
	public static void main(String args[]){
		for(char c: "An African Swallow".toCharArray()){
			System.out.print(c+" ");
		}
	}
}
