package electicBook;
import java.util.Timer;
/**
 * 输出一个模拟的菱形
 * @author Liuwei
 */
public class yongProgressMonitor {
	public static void main(String args[]){
		int n= 101;
		int o =(n/2);
		int t = 1;
		int step =2;
		for(int i=0;i<n;i++){
			for(int j=0;j<Math.abs(o);j++){
				System.out.print((char)32);
			}
			o--;
			for(int k=1;k<=t;k++){
				System.out.print("*");
			}
			t = t+step;
			if(t==n){
				step = -step;
			}
			System.out.println();
		}
	}
}
