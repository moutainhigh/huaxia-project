package electicBook;
/**
 * 测试final变量
 * final 不可变化，必须赋予初始值
 * @author liuwei
 */
public class FinalInstanceVaibaleTest {
	final int var1 = "疯狂Java讲义".length();
	final int var2 ;
	final int var3 ;
	{
		var2 = "轻量级Java EE企业应用实战".length();
	}
	public FinalInstanceVaibaleTest(){
		var3 = "疯狂XML讲义 ".length();
//		var3 = name.length();
	}
	public static void main(String args[]){
		FinalInstanceVaibaleTest fiv = new FinalInstanceVaibaleTest();
		System.out.println(fiv.var1);
		System.out.println(fiv.var2);
		System.out.println(fiv.var3);
		FinalInstanceVaibaleTest fiv2 = new FinalInstanceVaibaleTest();
		System.out.println(fiv2.var1);
		System.out.println(fiv2.var2);
		System.out.println(fiv2.var3);
	}
}
