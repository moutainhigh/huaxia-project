package codeQuerstion;
//扑克牌翻转
public class ShuffleCard {
	public static void main(String args[]){
		final int N = 52;
		for(int i =0;i<1000000000;i++){
			double t = Math.random()*N;
			int temp = (int)t;
//			System.out.println(temp);
			if(temp == 0 ){
				System.out.println("有0"+t);
			}
			if(temp ==52 ){
				System.out.println("有52"+temp);
			}
		}
	}
}
