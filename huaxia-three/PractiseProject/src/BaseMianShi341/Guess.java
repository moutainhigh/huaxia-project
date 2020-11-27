/**
 * Title: Guess.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月22日上午10:56:28
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:Guess2020年9月22日
 * @comments:
 * 在1-100范围内，现在开始验证哥德巴赫猜想：
4=2+2 6=3+3 8=3+5 10=3+7 12=5+7 
14=3+11 16=3+13 18=5+13 20=3+17 22=3+19 
24=5+19 26=3+23 28=5+23 30=7+23 32=3+29 
34=3+31 36=5+31 38=7+31 40=3+37 42=5+37 
44=3+41 46=3+43 48=5+43 50=3+47 52=5+47 
54=7+47 56=3+53 58=5+53 60=7+53 62=3+59 
64=3+61 66=5+61 68=7+61 70=3+67 72=5+67 
74=3+71 76=3+73 78=5+73 80=7+73 82=3+79 
84=5+79 86=3+83 88=5+83 90=7+83 92=3+89 
94=5+89 96=7+89 98=19+79 100=3+97 在1-100范围内，哥德巴赫猜想是正确的
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月22日上午10:56:28
 */
public class Guess {

	/**
	 * Constructor
	 */
	public Guess() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月22日上午10:56:28
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("在1-100范围内，现在开始验证哥德巴赫猜想：");
		if(Testify_Guess(1,1000000000)){
			System.out.println("在1-100范围内，哥德巴赫猜想是正确的");
		}else{
			System.out.println("哥德巴赫猜想是错误的");
		}
	}
	/**
	 * @Title: Testify_Guess
	 *@Description: TODO 
	 *@param low 判断范围内的数字是否都是哥德巴赫猜想
	 *@param high
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月22日上午11:03:01
	 */
	public static boolean Testify_Guess(int low,int high){
		int i,j = 0;
		boolean flag = true;
		for(i = low;i<=high;i++)
		{
			if(i%2 == 0 && i>2){
				//1-100之间选取大于2的偶数符合哥德巴赫猜想
				if(isGoldbach(i)){
					j++;
					if(j == 5){
						System.out.println();
						j = 0;
					}
				}else{
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	/**
	 * @Title: isGoldbach
	 *@Description: TODO 判断a是否是符合哥德巴赫猜想
	 *@param a
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月22日上午11:01:37
	 */
	public static boolean isGoldbach(int a){
		int i;
		boolean flag = false;
		for(i = 1;i<=a/2;i++){
			if(isPrime(i) && isPrime(a-i)){
				flag = true;
				System.out.print(a+"="+i+"+"+(a-i)+" ");
				break;
			}
		}
		return flag;
	}
	/**
	 * @Title: isPrime
	 *@Description: TODO
	 *@param i 判断数字是否是素数
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年9月22日上午10:57:02
	 */
	public static boolean isPrime(int i){
		int n;
		boolean flag = true;
		if(1 == i)
			flag = false;//1 本身不是素数
		for(n = 2;n<= i-1;n++)
			if(i %n ==0){
				flag = false;
				break;
			}
		return flag;
	}
}
