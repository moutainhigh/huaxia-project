/**
 * Title: Freq.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��21������3:29:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @class_name:Freq2020��8��21��
 * @comments:ʹ��mapͳ���ַ������ִ����ĳ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������3:29:58
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
	 *@Date: 2020��8��21������3:29:58
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
		System.out.println(m.size()+"����ͬ�ĵ��ʣ�");
		System.out.println(m);
	}
	
}
