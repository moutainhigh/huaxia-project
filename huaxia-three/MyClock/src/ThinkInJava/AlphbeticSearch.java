package ThinkInJava;
import java.util.Arrays;
import java.util.Collections;
/**
 * 进行查找，使用二分查找
 * @author Liuwei
 */
public class AlphbeticSearch {
	public static void main(String args[]){
		int length = 30;
		java.lang.String[] sa = new java.lang.String[length];
		ThinkInJava.RandomGenerator.String g=new RandomGenerator.String();
		for(int i=0;i<length;i++){
//			System.out.println(g.next()+" ");
			sa[i]=(g.next());
		}
		Arrays.sort(sa,String.CASE_INSENSITIVE_ORDER);//不区分大小写的排序
		System.out.println("Case-insensitive sort:"+Arrays.toString(sa));
		int index = Arrays.binarySearch(sa, sa[10],java.lang.String.CASE_INSENSITIVE_ORDER);
		System.out.println("Index"+index+"\n"+sa[index]);
		System.out.println();
	}
}
