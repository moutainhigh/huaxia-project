package SimpleBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Java示例在列表中旋转元素
 * 数字表示倒数第几个进行旋转
 * @author liwei
 */
public class Main {

	public static void main(String args[]){
		List list = Arrays.asList("one Two three Four five six".split(" "));
		System.out.println("List:"+list);
		Collections.rotate(list, 3);
		System.out.println("rotate: "+list);
		List list2 = Arrays.asList("one Two three Four five six".split(" "));
		System.out.println("list2:"+list2);
		Collections.rotate(list2, 2);
		System.out.println("rotate: "+list2);
		List list3 = Arrays.asList("one Two three Four five six".split(" "));
		System.out.println("list3:"+list3);
		Collections.rotate(list3, 1);
		System.out.println("rotate: "+list3);
		List list4 = Arrays.asList("one Two three Four five six".split(" "));
		System.out.println("list4:"+list4);
		Collections.rotate(list4,5);
		System.out.println("rotate: "+list4);
	}
}
