package codeQuerstion;
/**
 * 韩信点兵，人数不足百人
 * 3行一列多一人
 * 7人一列少两人
 * 5人一列正好，
 * 计算总人数
 * @author Liuwei
 *
 */
public class calculWar {
	public static void main(String args[]){
		for(int person = 0;person<100;person++){
			int a = person%3;
			int b = person%7;
			int c = person%5;
			if(a==1 && b == 5 && c==0){
				System.out.println("总人数"+person);
			}
		}
	}
}
