package JavaMultiThreadProgramming.mingribook.javaStruct;
/**
 * 转化为大写或者小写字母
 * @author liuwei
 *
 */
public class UpAndLower {
	public static void main(String args[]){
		String str = new String("abc DEF");
		String newstr = str.toLowerCase();
		String newstr2 = str.toUpperCase();
		System.out.println("原来的字符串"+str);
		System.out.println("小写字母"+newstr);
		System.out.println("大写字母"+newstr2);
	}
}
