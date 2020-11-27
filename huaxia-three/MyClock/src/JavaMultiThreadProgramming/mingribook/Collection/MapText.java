/**
 * Title: MapText.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日下午2:46:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @class_name:MapText2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日下午2:46:05
 */
public class MapText {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日下午2:46:05
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<>();
		
		Emp emp = new Emp("351","张三");
		Emp emp2 = new Emp("512","李四");
		Emp emp3 = new Emp("853","王一");
		Emp emp4 = new Emp("125","赵六");
		Emp emp5 = new Emp("341","黄七");
		
		map.put(emp4.getE_id(), emp4.getE_name());
		map.put(emp5.getE_id(), emp5.getE_name());
		map.put(emp.getE_id(), emp.getE_name());
		map.put(emp2.getE_id(), emp2.getE_name());
		map.put(emp3.getE_id(), emp3.getE_name());

		Set<String> set= map.keySet();
		Iterator<String> it = set.iterator();
		System.out.println("HashMap类实现的Map集合，无序");
		while(it.hasNext()){
			String str = (String) it.next();
			String name = (String) map.get(str);
			System.out.println(str+" " +name);
		}
		TreeMap<String,String> treemap = new TreeMap<>();
		treemap.putAll(map);
		Iterator<String> iter = treemap.keySet().iterator();
		System.out.println("Tree Map类实现的Map集合,键对象升序：");
		while(iter.hasNext()){
			String str =(String)iter.next();
			String name = (String)treemap.get(str);
			System.out.println(str+" " + name);
		}
	}

}
