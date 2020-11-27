/**
 * Title: Generics.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��18������2:07:57
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage;

/**
 * @class_name:Generics2020��9��18��
 * @comments:���������ķ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��18������2:07:57
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
	 *@author: LiuWei �����������
	 *@Date: 2020��9��18������2:09:02
	 */
	public void printTypes(){
		System.out.println("����T�Ķ������ͣ�"+t.getClass().getName());
		System.out.println("����v�Ķ�������"+v.getClass().getName());
	}
	/**
	 * @Title: getT
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��18������2:10:45
	 */
	T getT(){
		return t;
	}
	/**
	 * @Title: getV
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��9��18������2:10:50
	 */
	V getV(){
		return v;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��18������2:07:57
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Generics<Integer,Double> tv ;
		tv = new Generics<Integer,Double>(100,12.56);
		tv.printTypes();
		int num = tv.getT();
		System.out.println("num����ֵ��"+num);
		double dou = tv.getV();
		System.out.println("double������ֵ�ǣ�"+dou);
	}
}
