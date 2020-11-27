/**
 * Title: iteratorMapDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午4:14:25
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @class_name:iteratorMapDemo2020年9月11日
 * @comments:Map的迭代器的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午4:14:25
 */
public class iteratorMapDemo {

	/**
	 * Constructor
	 */
	public iteratorMapDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午4:14:25
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "张三");
		map.put(2, "李四");
		map.put(3, "王五");
		Set<Map.Entry<Integer,String>> set = map.entrySet();
		Iterator<Map.Entry<Integer,String>> iter = set.iterator();
		while(iter.hasNext()){
			Map.Entry<Integer, String> me = iter.next();
			System.out.println(me.getKey()+"-->"+me.getValue());
		}
	}
}
