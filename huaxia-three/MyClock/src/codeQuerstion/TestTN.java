package codeQuerstion;
/**
 * 1，2,3,4可以组成多少个互不重复
 * 并且不相同的三位数，都是多少？
 * @author Liuwei
 *
 */
public class TestTN {

	public static void main(String args[]){
		for(int i = 1;i <=4 ;i++){
			for(int j = 1; j <= 4;j++){
				if(j==i)continue;
				else{
					for(int k = 1;k <= 4;k++){
						if(k == j || k == i)continue;
						System.out.print(i*100+j*10+k+",");
					}
				}
			}
		}
	}
}
