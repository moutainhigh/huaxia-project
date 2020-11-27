package ThinkInJava;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
/**
 * 编辑测试Set
 * treeset是有顺序的
 * @author User
 *
 */
public class SetOfInteger {
	public static void main(String args[]){
		Random rand = new Random(47);
		Set<Integer> intset = new HashSet<Integer>();
		Set<Integer> intset2 = new TreeSet<Integer>();
		for(int i=0;i<100000;i++){
			intset.add(rand.nextInt(30));
			intset2.add(rand.nextInt(30));
		}
		System.out.println(intset);
		System.out.println(intset2);
	}
}
