/**
 * Title: HashSetTest_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日下午3:16:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @class_name:HashSetTest_12020年9月22日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日下午3:16:02
 */
public class HashSetTest_1 {

	/**
	 * Constructor
	 */
	public HashSetTest_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日下午3:16:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet hs = new HashSet();
		hs.add(Integer.valueOf(65));
		hs.add("A");
		hs.add(Integer.valueOf(66));
		hs.add("B");
		hs.add(Integer.valueOf(98));
		hs.add("a");
		hs.add(Integer.valueOf(99));
		hs.add("b");
		System.out.println("HashSet:"+hs);
		Iterator it = hs.iterator();
		System.out.println("用Integer迭代遍历：");
		while(it.hasNext()){
			System.out.print(it.next()+" ");
		}
	}

}
