package ProgramBeauty;
/**
 * 更定N，求M
 * 使N*M只包含1和0
 * @author liuwei
 */
public class NM {
	public static void NM(int n){
		for(int m=11;true;m++){
			if(contentZeroAndOne(n*m)){
				System.out.println("n*m= "+n+" "+"*"+m+"="+n*m);
				
			}
		}
	}
	public static boolean contentZeroAndOne(int n){
		String str = String.valueOf(n);
		for(int i =0;i<str.length();i++){
			String temp =str.substring(i,i+1);
			if(temp.equals("1") || temp.equals("0"))
				continue;
			else
				return false;
		}
		return true;
	}
	public static void main(String args[]){
		NM(21341234);
	}
}
