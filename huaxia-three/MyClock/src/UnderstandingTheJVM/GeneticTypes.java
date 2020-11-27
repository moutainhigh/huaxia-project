package UnderstandingTheJVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型的擦除
 * @author liuwei
 *
 */
public class GeneticTypes {
//	public static void method(List<String> list){
//		System.out.println("invoke  method(List<String> list)");
//	}
//	public static void method(List<Integer> list){
//		System.out.println("invoke  method(List<Integer> list)");
//	}
	public static String method(List<String> list){
		System.out.println("invoke  method(List<String> list)");
		return "";
	}
	public static int method(ArrayList<Integer> list){
		System.out.println("invoke  method(List<Integer> list)");
		return 1;
	}
	public static void main(String args[]){
		method(new ArrayList<String>());
		method(new ArrayList<Integer>());
	}
}
