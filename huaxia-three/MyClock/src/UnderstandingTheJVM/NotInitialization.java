package UnderstandingTheJVM;
/**
 * 测试常量的使用
 * 类并没有进行输出，初始化，但是变量存储在常量池里，可以只输出值
 * @author liuwei
 *
 */
public class NotInitialization {
	
	public static void main(String args[]){
		System.out.println(ConstClass.HEllOWORLD);
	}
}
class ConstClass{
	static{
		System.out.println("ConstClass inti!");
	}
	public static final String HEllOWORLD = "hello world!";
}