package codeQuerstion;
/**
 * 字符串压缩问题
 * 相同字符串压缩成字符和数字
 * aaaa 压缩成a4
 * 字符串中不包含数字,太難了，看答案都看不懂
 * @author liuwei
 *
 */
public class HWCompetition {

	public static void main(String args[]){
		String str = "abcddef";
		HWCompetition hwc= new HWCompetition();
		String result = hwc.compressStr(str);
		System.out.println(result);
	}
	public String compressStr(String str){
		StringBuffer sb =new StringBuffer();
		for(int i=0;i<str.length();i++){
			if(str.length() == 0) break;
			if(str.length() == 1) sb.append(str.charAt(i));
			for(int j = i+1;j<str.length();j++){
				if(str.charAt(i)==str.charAt(j)){
					if(j==str.length() -1){
						
					}
				}
			}
		}
		return sb.toString();
	}
}
