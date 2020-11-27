/**
 * Title: HashMapDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月11日下午3:34:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @class_name:HashMapDemo2020年9月11日
 * @comments: HashMap的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月11日下午3:34:42
 */
public class HashMapDemo {

	/**
	 * Constructor
	 */
	public HashMapDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月11日下午3:34:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Double> hm = new HashMap<String,Double>();
		hm.put("John Doe", new Double(3434.34));
		hm.put("Tom Smith", new Double(123.22));
		hm.put("Jane Baker", new Double(1378.00));
		hm.put("Todd Hall", new Double(99.22));
		hm.put("Ralph Smith", new Double(-19.08));
		Set<Entry<String,Double>> set = hm.entrySet();
		Iterator<Entry<String,Double>> i = set.iterator();
		while(i.hasNext()){
			Map.Entry<String,Double> me = (Map.Entry<String, Double>) i.next();
			System.out.print(me.getKey()+":");
			System.out.println(me.getValue());
		}
		System.out.println();
		double balance = ((Double)hm.get("John Doe")).doubleValue();
		hm.put("John Doe", new Double(balance+1000));
		System.out.println("John Doe 现在的资金："+hm.get("John Doe"));
	}
}
