package ThinkInJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
/**
 * 测试输入和输出
 * @author User
 *
 */
public class SimpleRead {
	public static BufferedReader input = new BufferedReader(new StringReader("Sir Robin of Camelog\n22 1.61803"));
	public SimpleRead() {
		
	}
	public static void main(String[] args) {
		try{
			System.out.println("What is your name?");
			String name = input.readLine();
			System.out.println(name);
			System.out.println("How old are you? what is your favorite double?");
			System.out.println("(input:<age><double>");
			String numbers = input.readLine();
			System.out.println(numbers);
			String[] numArray = numbers.split(" ");
			int age = Integer.parseInt(numArray[0]);
			double favorite = Double.parseDouble(numArray[1]);
			System.out.format("Hi %s.\n",name);
			System.out.format("In 5 years you will be %d.\n", age+5);
			System.out.format("My favorite double is %f.", favorite/2);
		}catch(IOException e){
			System.out.println("I/O exception");
		}
	}
}
