package electicBook;
/**
 * 测试final和static变量
 * final 不可变化，必须赋予初始值
 * static类只加载一次。只编译执行一次
 * @author liuwei
 */
public class FinalClassVaibaleTest {
	final static int var1 = "疯狂Java讲义".length();
	final static int var2 ;
//	final static int var3 ;
	static {
		var2 = "轻量级Java EE企业应用实战".length();
	}
	public FinalClassVaibaleTest(){
//		var3 = "疯狂XML讲义 ".length();
	}
	public static void main(String args[]){
		System.out.println(FinalClassVaibaleTest.var1);
		System.out.println(FinalClassVaibaleTest.var2);
//		System.out.println(FinalClassVaibaleTest.var3);
	}
}
