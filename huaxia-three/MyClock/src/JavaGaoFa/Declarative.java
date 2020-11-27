package JavaGaoFa;

import java.util.Arrays;
/**
 * 申明式编程
 * @author liuwei
 *
 */
public class Declarative {
	public static void main(String args[]){
		int[] iArr = {1,3,4,5,6,9,8,7,4,2};
		for(int i=0;i<iArr.length;i++){
			System.out.print(iArr[i]+" ");
		}
		System.out.println();
//		Arrays.stream(iArr).forEach(System.out::println);
	}
}
