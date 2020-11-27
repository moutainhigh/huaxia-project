package codeQuerstion;
/**
 * 公鸡5元一只
 * 母鸡3元一只
 * 小鸡1元三只
 * 问 100元怎样可以买100只鸡
 * @author 刘伟
 *
 */
public class JavaCalcul {
	//算法一
	public void calculOne(){
		for(int i=0;i<=20;i++){
			for(int j=0;j<34;j++){
				for(int k=0;k<=100;k++){
					if(k%3==0){
						if(i*5+j*3+(k/3) == 100){
							System.out.println("可以买公鸡"+i+"只，买母鸡"+j+"只，买小鸡"+k+"只。");
						}
					}
				}
			}
		}
	}
	
	public static void main(String args[]){
		JavaCalcul test = new JavaCalcul();
		test.calculOne();
	}
}
