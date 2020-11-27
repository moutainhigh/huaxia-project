/**
 * Title: HashtableDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午4:08:13
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.Hashtable;

/**
 * @class_name:HashtableDemo2020年9月11日
 * @comments:Hashtable 的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午4:08:13
 */
public class HashtableDemo {

	/**
	 * Constructor
	 */
	public HashtableDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午4:08:13
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String,Integer> numbers = new Hashtable<String,Integer>();
		numbers.put("one", new Integer(1));
		numbers.put("two", new Integer(2));
		numbers.put("three", new Integer(3));
		numbers.put("four", new Integer(4));
		Integer n = (Integer)numbers.get("two");
		if(n != null)
		{
			System.out.println("two="+n);
		}
	}
}
