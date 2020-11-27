package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Date;

/**
 * 常规类型格式化
 * @author liuwei
 *
 */
public class General {
	public static void main(String args[]){
		Date date = new Date();
		String str = String.format("%d", 400/2);//格式化十进制整数
		String str2 =String.format("%b", 3>5);//格式化boolean类型
		String str3 = String.format("%x", 200);//格式化十六进制整数
		System.out.println("400的一半是："+str);
		System.out.println("3>5正确吗："+str2);
		System.out.println("200的十六进制数是："+str3);
	}
}
