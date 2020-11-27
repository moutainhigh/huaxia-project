package codeQuerstion;
/**
 * 算法的好坏确实很重要，好的算法可以提升效率。提高计算能力。
 * 寻找Armstrong数字
 * 例如三位数
 * 1 的三次方 + 5的三次方+ 3 的三次方等于 153
 * 这样的数是Armstrong数字
 * 找出正数中的所有这样的额数字
 * @author liuwei
 *12341234
 */
public class Armstrong {
	public static void main(String args[]){
		String str="";
		int sum = 0;
		int temp = 0;
		int temp2 = 0;
		for(int number = 1;true;number++){
			str = String.valueOf(number);
			sum = 0;
			for(int i=0;i<str.length();i++){
				
				temp = Integer.parseInt(str.substring(i,i+1));
				temp2 = temp;
//				System.out.println(temp);
//				System.out.println(str.length());
				for(int j=0;j < str.length()-1;j++){
					temp2*=temp;
				}
//				System.out.println(temp2);
				sum = sum+temp2;
			}
//			System.out.println(sum + " "+number);
			if(sum == number){
				System.out.println(number);
			}
		}
	}
}
