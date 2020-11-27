package ThinkInJava;
/**
 * 找出所有的吸血鬼数字
 * 就是一个四位数，分解成两个二位数的乘积，
 * 这两位数的数字组成这个四位数
 * 1260=21*60
 * 1827=21*87
 * 2187= 27*81
 * @author liuwei
 *
 */
public class BloodNumber {
	public static void main(String args[]){
		for(int i=1000;i<=9999;i++){
			for(int j= 10;j<=99;j++){
				for(int k=10;k<=99 ;k++){
					if(k*j==i){
						if(judge(i,j,k))
						System.out.println(i+"="+j+"*"+k);
					}
				}
			}
		}
	}
	public static boolean judge(int i,int j,int k){
		String str1 = String.valueOf(i);
		String str2 = String.valueOf(j);
		String str3 = String.valueOf(k);
		String str4 = str2+str3;
		for(int h=0;h<4;h++){
			String str = str4.substring(h,h+1);
			for(int s=0;s<str1.length();s++){
				String sss= str1.substring(s,s+1);
				if(str.equals(sss)){
					str1=str1.replaceFirst(sss, "");
					
//				System.out.println(str1);
					break;
				}
			}
		}
		if(str1.equals("")) return true;
		return false;
	}
}
