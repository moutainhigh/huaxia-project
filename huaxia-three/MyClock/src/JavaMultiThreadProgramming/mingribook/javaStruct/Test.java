package JavaMultiThreadProgramming.mingribook.javaStruct;
/**
 * if选择结构
 * @author liuwei
 *
 */
public class Test {
	public static void main(String args[]){
		String  str = "明日科技";
		switch(str){
		case "明日":
			System.out.println("明日书网。。。。");
			break;
		case "明日科技":
			System.out.println("明日科技有限公司	");
			break;
		default:
			System.out.println("以上条件都不是");
		}
		System.out.println("Hello World!");
	}
}
