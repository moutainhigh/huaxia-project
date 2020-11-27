package ThinkInJava;
import org.junit.Test;
/**
 * 测试方法的返回值会输出
 * @author liuwei
 *
 */
public class AtUnitExample1 {
	public AtUnitExample1() {
		// TODO Auto-generated constructor stub
	}
	public String methodOne(){
		return "This is method One";
	}
	public  int methodTwo(){
		System.out.println("This is methodTwo");
		return 2;
	}
	public boolean methodOneTest(){
		return methodOne().equals("This is method One");
	}
	@Test
	public void testOne(){
		System.out.println(methodOneTest());
		System.out.println(methodOne());
		System.out.println(methodTwo());
	}
//	public static void main(String args[]){
//		OSExecute.command("");
//	}
}
