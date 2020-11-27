package JavaMultiThreadProgramming.mingribook.javaStruct;

public class StartOrEnd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num1 = "22045612";
		String num2 = "21304578";
		boolean b = num1.startsWith("22");
		boolean b2 = num1.endsWith("78");
		boolean b3 = num2.startsWith("22");
		boolean b4 = num2.endsWith("78");
		System.out.println("字符串num1是以22开始的么？"+b);
		System.out.println("字符串num1是以78结束的么？"+b2);
		System.out.println("字符串num2是以22开始的么？"+b3);
		System.out.println("字符串num2是以22开始的么？"+b4);

	}

}
