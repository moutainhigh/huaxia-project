package ProgramBeauty;
/**
 * 統計1的個數
 * 例如1 - 10
 * 1 2 3 4 5 6 7 8 9 10 11 12 
 * 1 出現5次
 * @author liuwei
 *先計算每個數中1的個數
 *然后统计一段区间中的数字
 */
public class Count1Number {
	//每个数中的1个个数
	int count(int n){
		int inum = 0;
		while(n !=0){
			inum += (n%10 ==1)?1:0;
			n/=10;
		}
		return inum;
	}
	//一段数字区间中1的个数
	int cou(int n){
		int icount =0;
		for(int i=1;i<=n;i++){
			icount += count(i);
		}
		return icount;
	}
	public static void main(String args[]){
		Count1Number Count1Number = new Count1Number();
		System.out.println(Count1Number.cou(10000));
	}
}
