package projectExample;
import java.util.Scanner;
/**
 * 测试键盘的输入
 * @author Liuwei
 */
public class ScannerKeyBoardTest {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			System.out.println("键盘输入的内容是:"+sc.next());
		}
	}
}
