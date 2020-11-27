package ThinkInJava;
import java.util.Arrays;
import java.util.Collections;
/**
 * 数组的排序算法
 * @author Liuwe
 */
public class StringSorting {
	public static void main(String args[]){
		int length = 20;
		java.lang.String[] sa = new java.lang.String[length];
		ThinkInJava.RandomGenerator.String g=new RandomGenerator.String();
		for(int i=0;i<length;i++){
//			System.out.println(g.next()+" ");
			sa[i]=(g.next());
		}
		System.out.println("Before sort:"+Arrays.toString(sa));
		Arrays.sort(sa);//大写字母在前
		System.out.println("After sort:"+Arrays.toString(sa));
		Arrays.sort(sa,Collections.reverseOrder());
		System.out.println("Reverse sort:"+Arrays.toString(sa));
		Arrays.sort(sa,String.CASE_INSENSITIVE_ORDER);//不区分大小写的排序
		System.out.println("Case-insensitive sort:"+Arrays.toString(sa));
//		for(int i=0;i<length ;i++){
//			System.out.print(sa[i].toString()+" ");
//		}
		System.out.println();
	}
}
