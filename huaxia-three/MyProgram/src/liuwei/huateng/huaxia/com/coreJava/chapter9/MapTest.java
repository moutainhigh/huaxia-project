/**
 * Title: MapTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日上午9:12:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

import java.util.HashMap;
import java.util.Map;

/**
 * @class_name:MapTest2020年1月13日
 * @comments:测试map集合
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日上午9:12:01
 */
public class MapTest {

	/**
	 * 
	 */
	public MapTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月13日上午9:12:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Employee> staff = new HashMap<>();
		staff.put("144-25-5464",new Employee("Amy Lee"));
		staff.put("567-24-2546",new Employee("Harry Hacker"));
		staff.put("157-62-7935",new Employee("Gray Cooper"));
		staff.put("456-62-5527",new Employee("Francesca Cruz"));
		//print all entries
		System.out.println(staff);
		//remove an entry 
		staff.remove("567-24-2546");
		//replaze an entry
		staff.put("456-62-5527",new Employee("Francesca Miller"));
		//look up a value 
		System.out.println(staff.get("157-62-7935"));
		//iterate through all entries
		for(String key :staff.keySet()){
			System.out.println("key="+key+",value="+staff.get(key));
		}
	}

}
