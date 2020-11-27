/**
 * Title: ThreeSetDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午2:57:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.TreeSet;

/**
 * @class_name:ThreeSetDemo2020年9月11日
 * @comments:TreeSet的使用，树型结构，是顺序存储
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午2:57:38
 */
public class ThreeSetDemo {

	/**
	 * Constructor
	 */
	public ThreeSetDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午2:57:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<String> ts = new TreeSet<String>();
		ts.add("C");
		ts.add("A");
		ts.add("B");
		ts.add("E");
		ts.add("F");
		ts.add("D");
		System.out.println(ts);
	}

}
