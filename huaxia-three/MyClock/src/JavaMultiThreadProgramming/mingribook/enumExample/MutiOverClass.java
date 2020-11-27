/**
 * Title: MutiOverClass.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月16日下午4:37:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.enumExample;

import java.util.HashMap;
import java.util.Map;

/**
 * @class_name:MutiOverClass2019年10月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月16日下午4:37:39
 */
public class MutiOverClass<K, V> {
	public Map<K,V> m = new HashMap<K,V>();
	/**
	 * @Title: put
	 *@Description: TODO
	 *@param k
	 *@param v
	 *@author: LiuWei
	 *@Date: 2019年10月16日下午4:40:34
	 */
	public void put(K k,V v){
		m.put(k, v);
	}
	/**
	 * @Title: get
	 *@Description: TODO
	 *@param k
	 *@return
	 *@author: LiuWei
	 *@Date: 2019年10月16日下午4:40:38
	 */
	public V get(K k){
		return m.get(k);
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月16日下午4:37:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MutiOverClass<Integer,String> mu = new MutiOverClass<Integer,String>();
		for(int i=0;i<5;i++){
			mu.put(i, "我是集合成员"+i);
		}
		for(int i=0;i<mu.m.size();i++){
			System.out.println(mu.get(i));
		}
	}

}
