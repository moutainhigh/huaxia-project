/**
 * Title: Abnormal.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午10:37:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:Abnormal2020年9月23日
 * @comments:
Z=2
Exception in thread "main" java.lang.NullPointerException
	at BaseMianShi341.Abnormal4.main(Abnormal4.java:48)
 * @param: catch没有捕获异常，程序方法跳出，异常终止了。
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午10:37:02
 */
public class Abnormal4 {

	/**
	 * Constructor
	 */
	public Abnormal4() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午10:37:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 2;
		String b[] = new String[2];
		try{
			int z = 5/a;
			System.out.println("Z="+z);
			b[0].toLowerCase();
		}catch(ArithmeticException e){
			System.out.println("0不能作为被除数。");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("arry[]长度是5，数组下标越界了");
		}
		System.out.println("没有异常运行成功了。");
	}

}
