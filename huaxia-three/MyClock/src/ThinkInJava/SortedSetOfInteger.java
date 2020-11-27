package ThinkInJava;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 * 测试排序的set,Treeset,存储在红黑树中，看起来是有顺序的。
 * @author liuwei
 */
public class SortedSetOfInteger {
	public static void main(String args[]){
		Random rand = new Random(47);
		SortedSet<Integer> intset = new TreeSet<Integer>();
		for(int i=0;i<10000;i++){
			intset.add(rand.nextInt(30));
		}
		System.out.println(intset);
	}
}
