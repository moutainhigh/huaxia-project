/**
 * Title: ShuffleTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��13������10:23:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.coreJava.chapter9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @class_name:ShuffleTest2020��1��13��
 * @comments:����������û���������㷨
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��13������10:23:37
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
	 *@Date: 2020��1��13������10:23:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = new ArrayList<>();
		for(int i=0;i<= 49 ;i++)
		{
			numbers.add(i);
		}
		System.out.println(numbers);
		//shuffles�㷨��ָ������Դ���б�����û�
		Collections.shuffle(numbers);
		System.out.println(numbers);
		List<Integer> winningCombination = numbers.subList(0, 6);
		//sort�����򷽷�
		Collections.sort(winningCombination);
		System.out.println(winningCombination);
	}

}
