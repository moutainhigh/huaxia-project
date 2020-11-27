package ThinkInJava;

import java.util.Random;
/**
 * 测试练习程序
 * @author User
 *
 */
public class TestSwitch2 {
	public static void main(String args[]){
		Random rand = new Random(47);
		for(int i=0;i<100;i++){
			int c = rand.nextInt(26)+'a';
			switch(c){
			case 'a':
			case 'e':
			case 'i':
			case '0':
			case 'u':System.out.println("AEIOU"+i); break;
			case 'y':
			case 'w':System.out.println("YW"+i);break;
			default : System.out.println("HelloWorld！");
			}
		}
	}
}
