/**
 * Title: Muster.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日上午10:53:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @class_name:Muster2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日上午10:53:44
 */
public class Muster {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日上午10:53:45
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			String str= (String)it.next();
			System.out.println(str);
		}
	}

}
