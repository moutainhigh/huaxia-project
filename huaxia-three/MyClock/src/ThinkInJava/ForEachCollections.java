package ThinkInJava;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
public class ForEachCollections {
	public static void main(String args[]){
		Collection<String> cs = new LinkedList<String>();
		Collections.addAll(cs,"Task the long way home".split(" "));
		for(String s:cs){
			System.out.print(" "+s+"   ");
		}
		setOfInteger();
	}
	public static void  setOfInteger(){
		Random rand = new Random();
		Set<Integer> intset = new HashSet<Integer>();
		for(int i =0;i<100000000;i++){
			intset.add(rand.nextInt(100));
		}
		System.out.println(intset);
	}
}
