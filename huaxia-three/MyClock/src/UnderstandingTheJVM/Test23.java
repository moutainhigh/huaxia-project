package UnderstandingTheJVM;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 自动拆箱装箱机制
 * @author liuwei
 */
public class Test23 {
	public static void main(String args[]){
		List<Integer> list = Arrays.asList(1,2,3,4);
		int sum =0;
		for(int i:list){
			sum+=i;
		}
		System.out.println(sum);
		sum();
	}
	public static void sum(){
		List list = Arrays.asList(new Integer[]{
				Integer.valueOf(1),
				Integer.valueOf(2),
				Integer.valueOf(3),
				Integer.valueOf(4)
		});
				int sum = 0;
		for(Iterator localIterator = list.iterator();localIterator.hasNext();){
			int  i= ((Integer)localIterator.next()).intValue();
			sum+=i;
		}
		System.out.println(sum);
		}
	}

