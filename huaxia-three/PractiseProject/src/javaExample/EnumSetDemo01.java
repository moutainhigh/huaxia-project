/**
 * Title: EnumSetDemo01.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日上午11:26:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * 
 * @class_name:Color62020年8月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午11:26:39
 */
enum Color6{红色,绿色,蓝色};
/**
 * @class_name:EnumSetDemo012020年8月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午11:26:03
 */
public class EnumSetDemo01 {

	/**
	 * Constructor
	 */
	public EnumSetDemo01() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月27日上午11:26:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumSet<Color6> set = EnumSet.allOf(Color6.class);
		Iterator<Color6> iter = set.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}
