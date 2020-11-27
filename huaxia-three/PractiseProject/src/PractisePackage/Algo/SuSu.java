/**
 * Title: SuSu.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月10日上午10:30:58
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:SuSu2020年8月10日
 * @comments: 使用break语句打印3-100之间的素数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月10日上午10:30:58
 */
public class SuSu {

	/**
	 * Constructor
	 */
	public SuSu() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月10日上午10:30:58
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		susu();
	}
	public static void susu(){
		System.out.println("3-100之间的素数有：");
		boolean isprime;
		int iCount = 0;
		for(int i= 3 ;i<=100;i++){
			isprime = true;
			for(int j = 2;j<i/2;j++){
				if(i % j == 0){
					isprime = false;
					break;
				}
			}
			if(isprime)
			{
				System.out.print(i+"\t");
				iCount++;
				if(iCount % 6 ==0)
				System.out.println();
			}
		}
	}
}	
