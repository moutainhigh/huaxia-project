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
public class Pair<T> {
	T first;
	T second;
	/**
	 * Constructor
	 */
	public Pair(T first,T second) {
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
	public T getFirst(){
		return first;
	}
	/**
	 * @Title: getSecond
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月6日下午2:42:13
	 */
	public T getSecond(){
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
		Pair<Integer> minmax = new Pair<Integer>(1,10);
		Integer min = minmax.getFirst();
		Integer max = minmax.getSecond();
		System.out.println("min="+min);
		System.out.println("max="+max);
	}

}
