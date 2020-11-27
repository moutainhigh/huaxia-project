package ThinkInJava;
import java.util.Arrays;
/**
 * 从排序的数组中进行二分查找
 * @author Liuwei
 */
public class ArraySearching {
	public static void main(String args[]){
		int length = 25;
		Generator<Integer> gen = new RandomGenerator.Integer(1000);
		Integer[] a = new Integer[length];
		for(int i=0;i<length;i++){
			a[i]= gen.next();
		}
		Arrays.sort(a);
		System.out.println("Sorted array:"+Arrays.toString(a));
		while(true){
			int r = gen.next();
			int location = Arrays.binarySearch(a, r);
			if(location>=0){
				System.out.println("Location of"+r+" is"+location+",a["+location+"]="+a[location]);
				break;
			}
		}
	}
}
