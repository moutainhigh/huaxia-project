package codeQuerstion;
/**
 * 打印三位数的水仙花数
 * @author Liuwei
 *
 */
public class WaterFlower {

	public static void main(String args[]){
		for(int i=100 ;i <= 999; i++){
			int ia = i/100;
			int ib = i%100/10;
			int ic = i%10;
			
			if((ia*ia*ia + ib*ib*ib + ic*ic*ic) == i){
				System.out.println(i);
			}
		}
	}
}
