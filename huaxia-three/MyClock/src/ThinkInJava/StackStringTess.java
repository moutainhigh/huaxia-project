package ThinkInJava;

import org.junit.Test;
/**
 *  使用注解进行单元测试，测试程序
 * @author User
 *
 */
public class StackStringTess extends  StackL<String>{
	
	public StackStringTess() {
		// TODO Auto-generated constructor stub
	}
//	@Test
	public void _push(){
		push("one");
		assert top().equals("one");
		push("two");
		assert top().equals("two");
		System.out.println(top());
	}
//	@Test
	public void _test(){
		push("A");
		push("B");
		push("C");
		push("D");
		push("E");
		push("F");
		for(int i=0;i<5;i++){
			System.out.println(pop());
		}
		System.out.println(top());
	}
	@Test
	public void test2(){
		for(int i=0;i<100;i++){
			push(""+i);
		}
		for(int i=0;i<100;i++){
			System.out.println(pop());
		}
	}
}
