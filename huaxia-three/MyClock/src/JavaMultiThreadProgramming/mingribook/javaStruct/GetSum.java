package JavaMultiThreadProgramming.mingribook.javaStruct;
/**
 * while循环的使用
 * @author liuwei
 *
 */
public class GetSum {
	public static void main(String args[]){
		int x= 1;
		int sum =0;
		while(x<=10){
			sum = sum+x;
			x++;
		}
		System.out.println("sum="+sum);
	}
}