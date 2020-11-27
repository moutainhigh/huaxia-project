/**
 * Title: EnumMapDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������11:17:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

import java.util.EnumMap;
import java.util.Map;

/**
 * 
 * @class_name:Color52020��8��27��
 * @comments:Color5
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������11:18:12
 */
enum Color5{��ɫ,��ɫ,��ɫ};
/**
 * @class_name:EnumMapDemo2020��8��27��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������11:17:24
 */
public class EnumMapDemo {

	/**
	 * Constructor
	 */
	public EnumMapDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��27������11:17:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumMap<Color5,String> eMap = new EnumMap<Color5,String>(Color5.class);
		eMap.put(Color5.��ɫ, "RED");
		eMap.put(Color5.��ɫ,"GREEN");
		eMap.put(Color5.��ɫ, "BLUE");
		for(Map.Entry<Color5,String> me:eMap.entrySet()){
			System.out.println(me.getKey()+"-->"+me.getValue());
		}
	}

}
