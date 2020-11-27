/**
 * Title: Pair.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��6������2:40:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

/**
 * @class_name:Pair2020��8��6��
 * @comments:���͵�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��6������2:40:21
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
	 *@Date: 2020��8��6������2:41:59
	 */
	public U getFirst(){
		return first;
	}
	/**
	 * @Title: getSecond
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��6������2:42:13
	 */
	public V getSecond(){
		return second;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��6������2:40:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pair2<String,Integer> minmax = new Pair2<String,Integer>("����",100);
		String min = minmax.getFirst();
		Integer max = minmax.getSecond();
		System.out.println("name="+min);
		System.out.println("data="+max);
	}

}
