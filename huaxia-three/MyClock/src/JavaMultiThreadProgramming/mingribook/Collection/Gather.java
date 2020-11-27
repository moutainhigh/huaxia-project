/**
 * Title: Gather.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年10月15日上午11:12:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMultiThreadProgramming.mingribook.Collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @class_name:Gather2019年10月15日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年10月15日上午11:12:52
 */
public class Gather {

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年10月15日上午11:12:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		int i=(int)(Math.random()*list.size());
		System.out.println("随机获取数组中的元素："+list.get(i));
		list.remove(2);
		System.out.println("现在数组中的元素是：");
		for(int j=0;j<list.size();j++){
			System.out.println(list.get(j));
		}
	}

}
