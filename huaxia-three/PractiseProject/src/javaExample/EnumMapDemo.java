/**
 * Title: EnumMapDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日上午11:17:24
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

import java.util.EnumMap;
import java.util.Map;

/**
 * 
 * @class_name:Color52020年8月27日
 * @comments:Color5
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午11:18:12
 */
enum Color5{红色,绿色,蓝色};
/**
 * @class_name:EnumMapDemo2020年8月27日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日上午11:17:24
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
	 *@Date: 2020年8月27日上午11:17:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumMap<Color5,String> eMap = new EnumMap<Color5,String>(Color5.class);
		eMap.put(Color5.红色, "RED");
		eMap.put(Color5.绿色,"GREEN");
		eMap.put(Color5.蓝色, "BLUE");
		for(Map.Entry<Color5,String> me:eMap.entrySet()){
			System.out.println(me.getKey()+"-->"+me.getValue());
		}
	}

}
