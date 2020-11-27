/**
 * Title: ShuffleTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月13日上午10:23:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @class_name:ShuffleTest2020年1月13日
 * @comments:了李彪的随机置换和排序的算法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月13日上午10:23:37
 */
public class ShuffleTest {

	/**
	 * 
	 */
	public ShuffleTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月13日上午10:23:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = new ArrayList<>();
		for(int i=0;i<= 49 ;i++)
		{
			numbers.add(i);
		}
		System.out.println(numbers);
		//shuffles算法是指定数据源对列表进行置换
		Collections.shuffle(numbers);
		System.out.println(numbers);
		List<Integer> winningCombination = numbers.subList(0, 6);
		//sort是排序方法
		Collections.sort(winningCombination);
		System.out.println(winningCombination);
	}

}
