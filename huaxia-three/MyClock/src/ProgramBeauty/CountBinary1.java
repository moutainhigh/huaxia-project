package ProgramBeauty;
/**
 * 统计字符串中1的个数
 * @author liuwei
 */
public class CountBinary1 {
	static int count(String str){
		int num =0;
		int temp;
		for(int i=0;i<str.length();i++){
			temp = Integer.parseInt(String.valueOf(str.charAt(i)));
			if(temp == 1) num++;
		}
		return num;
	}
	public static void main(String args[]){
		System.out.println(count("111000111001001"));
	}
}
