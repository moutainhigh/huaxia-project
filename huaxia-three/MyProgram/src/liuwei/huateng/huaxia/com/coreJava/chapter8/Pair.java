/**
 * Title: Pair.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��31������4:20:05
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter8;

/**
 * @class_name:Pair2019��12��31��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��31������4:20:05
 */
public class Pair<T>{
	private T first;
	private T second;
	/**
	 * 
	 */
	public Pair() {
		// TODO Auto-generated constructor stub
		first = null;
		second = null;
	}
	public Pair(T first,T second)
	{
		this.first = first;
		this.second = second;
	}
	public T getFirst() {
		return first;
	}
	public T getSecond() {
		return second;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}
}
