/**
 * Title: TreeMapDemo03.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午3:54:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @class_name:TreeMapDemo032020年9月11日
 * @comments:tree,树是一个顺序结构
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午3:54:42
 */
public class TreeMapDemo03 {

	/**
	 * Constructor
	 */
	public TreeMapDemo03() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午3:54:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeMap<Integer,String> tm  = new TreeMap<Integer,String>();
		tm.put(new Integer(10000-2000), "张三");
		tm.put(new Integer(10000-1500), "李四 ");
		tm.put(new Integer(10000-2500), "王五");
		tm.put(new Integer(10000-5000), "赵六");
		Collection<String> col =tm.values();
		Iterator<String> i = col.iterator();
		System.out.println("按工资由低到高顺序输出:");
		while(i.hasNext()){
			String value = i.next();
			System.out.println(value+":"+(tm.toString()));
		}
	}

}
