/**
 * Title: Freq.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月21日下午3:29:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @class_name:Freq2020年8月21日
 * @comments:使用map统计字符串出现次数的程序
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日下午3:29:58
 */
public class Freq {

	/**
	 * Constructor
	 */
	public Freq() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月21日下午3:29:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Integer> m = new HashMap<String,Integer>();
		Scanner input  = new Scanner(System.in);
		String str = input.nextLine();
		String[] arg = str.split(" ");
		System.out.println(arg.toString());
		for(String a: arg){
			Integer freq = m.get(a);
			m.put(a, (freq == null)?1:freq+1);
		}
		System.out.println(m.size()+"个不同的单词：");
		System.out.println(m);
	}
	
}
