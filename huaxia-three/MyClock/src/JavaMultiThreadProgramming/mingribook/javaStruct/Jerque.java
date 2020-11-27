package JavaMultiThreadProgramming.mingribook.javaStruct;

import java.util.Date;

/**
 * 字符串生成器
 * @author liuwei
 *
 */
public class Jerque {
	public static void main(String args[]){
		String str = "";
		long starTime = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			str = str+i;
		}
		long endTime = System.currentTimeMillis();
		long time = endTime - starTime;
		System.out.println("String 消耗时间："+time);
		StringBuilder builder = new StringBuilder("");
		starTime = System.currentTimeMillis();
		for(int j=0;j<10000;j++){
			builder.append(j);
		}
		endTime = System.currentTimeMillis();
		time = endTime-starTime;
		System.out.println("StringBuilder消耗时间"+time);
	}
}
