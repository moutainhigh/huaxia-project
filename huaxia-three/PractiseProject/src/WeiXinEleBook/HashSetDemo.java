/**
 * Title: HashSetDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午2:53:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.HashSet;

/**
 * @class_name:HashSetDemo2020年9月11日
 * @comments: hashset类型的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午2:53:33
 */
public class HashSetDemo {

	/**
	 * Constructor
	 */
	public HashSetDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午2:53:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> hs = new HashSet<String>();
		hs.add("B");
		hs.add("A");
		hs.add("D");
		hs.add("E");
		hs.add("C");
		hs.add("F");
		System.out.println(hs);
	}

}
