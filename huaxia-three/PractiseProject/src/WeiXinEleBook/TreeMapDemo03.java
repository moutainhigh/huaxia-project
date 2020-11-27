/**
 * Title: TreeMapDemo03.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��11������3:54:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package WeiXinEleBook;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * @class_name:TreeMapDemo032020��9��11��
 * @comments:tree,����һ��˳��ṹ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��11������3:54:42
 */
public class TreeMapDemo03 {

	/**
	 * Constructor
	 */
	public TreeMapDemo03() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��11������3:54:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeMap<Integer,String> tm  = new TreeMap<Integer,String>();
		tm.put(new Integer(10000-2000), "����");
		tm.put(new Integer(10000-1500), "���� ");
		tm.put(new Integer(10000-2500), "����");
		tm.put(new Integer(10000-5000), "����");
		Collection<String> col =tm.values();
		Iterator<String> i = col.iterator();
		System.out.println("�������ɵ͵���˳�����:");
		while(i.hasNext()){
			String value = i.next();
			System.out.println(value+":"+(tm.toString()));
		}
	}

}
