/**
 * Title: BlankDelete.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午5:15:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @class_name:BlankDelete2020年9月18日
 * @comments:去除字符串中的空格、回车、换行符、制表符
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午5:15:42
 */
public class BlankDelete {

	/**
	 * Constructor
	 */
	public BlankDelete() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午5:15:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
	System.out.println("请输入测试字符串");
	String str1 = scan.nextLine();
	System.out.println("去除字符串中的空格、回车、换行符、制表符");
	System.out.println(BlankDelete.replaceBlank(str1));
	}
	/**
	 * @Title: replaceBlank
	 *@Description: TODO
	 *@param str
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午5:16:41
	 */
	public static String replaceBlank(String str){
		String dest = "";
		if(str != null){
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
