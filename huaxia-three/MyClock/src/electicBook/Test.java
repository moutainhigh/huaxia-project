package electicBook;
/**
 * 一个表达式，j=j++;
 * 相当于temp= j
 *    j=j+1;
 *    j=temp;
 *  j=++j;相当于
 *  j=j+1;
 *  temp=j;
 *  j=temp;
 * @author liuwei
 *
 */
public class Test {
	public static void main(String args[]){
		int j=0;
		for(int i=0;i<100;i++){
//			j= j++;
			j= ++j;
		}
		System.out.println(j);
	}
}
