/**
 * Title: BlankDelete.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������5:15:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @class_name:BlankDelete2020��9��18��
 * @comments:ȥ���ַ����еĿո񡢻س������з����Ʊ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������5:15:42
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
	 *@Date: 2020��9��18������5:15:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
	System.out.println("����������ַ���");
	String str1 = scan.nextLine();
	System.out.println("ȥ���ַ����еĿո񡢻س������з����Ʊ��");
	System.out.println(BlankDelete.replaceBlank(str1));
	}
	/**
	 * @Title: replaceBlank
	 *@Description: TODO
	 *@param str
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��18������5:16:41
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
