/**
 * Title: UpdateStu.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日上午11:21:14
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @class_name:UpdateStu2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日上午11:21:14
 */
public class UpdateStu {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日上午11:21:14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<>();
		map.put("01", "李同学");
		map.put("02", "魏同学");
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		System.out.println("key集合中的元素：");
		while(it.hasNext()){
			System.out.println(it.next());
		}
		Collection<String> coll =map.values();
		it = coll.iterator();
		System.out.println("values集合中的元素");
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
