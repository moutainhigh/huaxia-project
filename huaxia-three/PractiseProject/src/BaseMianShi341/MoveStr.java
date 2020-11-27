/**
 * Title: MoveStr.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日下午1:41:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

import java.util.Scanner;

/**
 * @class_name:MoveStr2020年9月22日
 * @comments:
 * @param字符串的，把*号移动到前方，并且不改变原有字符串的顺序
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午1:41:31
 */
public class MoveStr {

	/**
	 * Constructor
	 */
	public MoveStr() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午1:41:31
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请输入字符串：");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		char[] ch = str.toCharArray();
		System.out.println("处理前的字符串为："+str);
		int sum = beginMove(ch);
		System.out.println("字符串中‘*’的数量为："+sum);
	}
	/**
	 * @Title: beginMove
	 *@Description: TODO
	 *@param ch
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午1:46:33
	 */
	public static int beginMove(char[] ch){
		int i,j= ch.length -1;
		for(i =j;j>=0;j--){
			if(ch[i] != '*'){
				i--;
			}else if(ch[j] != '*'){
				ch[i] = ch[j];
				ch[j] = '*';
				i--;
			}
		}
		System.out.println("处理后字符串为：");
		for(int k =0;k<ch.length;k++){
			System.out.print(ch[k]);
		}
		System.out.println();
		return i+1;
	}
}
