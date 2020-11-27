package ThinkInJava;
import org.junit.Test;
/**
 *  注解开发，测试注解
 * @author liuwei
 *
 */
public class Testable {

	public Testable() {
		// TODO Auto-generated constructor stub
	}
	public void execute(){
		System.out.println("Executing..");
	}
	@Test
	public void testExecute(){
		execute();
	}
}
