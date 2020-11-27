package ThinkInJava;
import java.util.Scanner;
/**
 * 测试输入和输出类
 * @author User
 *
 */
public class BetterRead {
	public BetterRead() {
	}
	public static void main(String[] args) {
		try{
			Scanner stdin = new Scanner(SimpleRead.input);
			System.out.println("What is your name?");
			String name = stdin.nextLine();
			System.out.println(name);
			System.out.println("How old are you? what is your favorite double?");
			System.out.println("(input:<age><double>");
			int age = stdin.nextInt();
			double favorite =stdin.nextDouble();
			System.out.println(age);
			System.out.println(favorite);
			System.out.format("Hi %s.\n",name);
			System.out.format("In 5 years you will be %d.\n", age+5);
			System.out.format("My favorite double is %f.", favorite/2);
		}catch(Exception e){
			System.out.println("I/O exception");
		}
	}
}
