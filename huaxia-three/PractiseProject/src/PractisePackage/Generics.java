/**
 * Title: Generics.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月18日下午2:07:57
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

/**
 * @class_name:Generics2020年9月18日
 * @comments:两个参数的泛型
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月18日下午2:07:57
 */
public class Generics<T, V> {
	T t;
	V v;
	/**
	 * Constructor
	 */
	public Generics(T t1,V v1) {
		// TODO Auto-generated constructor stub
		t = t1;
		v = v1;
	}
	/**
	 * @Title: printTypes
	 *@Description: TODO
	 *@author: LiuWei 输出对象类型
	 *@Date: 2020年9月18日下午2:09:02
	 */
	public void printTypes(){
		System.out.println("参数T的对象类型："+t.getClass().getName());
		System.out.println("参数v的对象类型"+v.getClass().getName());
	}
	/**
	 * @Title: getT
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午2:10:45
	 */
	T getT(){
		return t;
	}
	/**
	 * @Title: getV
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午2:10:50
	 */
	V getV(){
		return v;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月18日下午2:07:57
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Generics<Integer,Double> tv ;
		tv = new Generics<Integer,Double>(100,12.56);
		tv.printTypes();
		int num = tv.getT();
		System.out.println("num变量值是"+num);
		double dou = tv.getV();
		System.out.println("double变量的值是："+dou);
	}
}
