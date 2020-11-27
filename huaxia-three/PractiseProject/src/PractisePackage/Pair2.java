/**
 * Title: Pair.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月6日下午2:40:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

/**
 * @class_name:Pair2020年8月6日
 * @comments:泛型的使用
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月6日下午2:40:21
 */
public class Pair2<U,V> {
	U first;
	V second;
	/**
	 * Constructor
	 */
	public Pair2(U first,V second) {
		// TODO Auto-generated constructor stub
		this.first = first;
		this.second = second;
	}
	/**
	 * @Title: getFirst
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午2:41:59
	 */
	public U getFirst(){
		return first;
	}
	/**
	 * @Title: getSecond
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午2:42:13
	 */
	public V getSecond(){
		return second;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午2:40:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pair2<String,Integer> minmax = new Pair2<String,Integer>("老马",100);
		String min = minmax.getFirst();
		Integer max = minmax.getSecond();
		System.out.println("name="+min);
		System.out.println("data="+max);
	}

}
