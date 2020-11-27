/**
 * Title: SetTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月2日上午10:05:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @class_name:SetTest2020年1月2日
 * @comments:集合测试
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月2日上午10:05:44
 */
public class SetTest {

	/**
	 * 
	 */
	public SetTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月2日上午10:05:44
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> words = new HashSet<>();
		long totalTime = 0;
		try(Scanner in =new Scanner(System.in))
		{
			while(in.hasNext())
			{
				 String word = in.next();
				long callTime = System.currentTimeMillis();
				words.add(word);
				System.out.println(word);
				callTime = System.currentTimeMillis()-callTime;
				totalTime += callTime;
				if(word.equals("exit")){
					break;
				}
			}
		}
		Iterator<String> iter = words.iterator();
		for(int i= 1;i<=20&& iter.hasNext();i++)
		System.out.println(iter.next());
		System.out.println("...");
		System.out.println(words.size()+" distinct words. "+totalTime+" milliseconds");
	}
}
