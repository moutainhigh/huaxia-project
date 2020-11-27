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
0
1
3
4
6
arry[]长度是5，数组下标越界了
没有异常运行成功了。。
 * @param: catch捕获异常
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午10:37:02
 */
public class Abnormal3 {

	/**
	 * Constructor
	 */
	public Abnormal3() {
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
		int arry[] ={0,1,3,4,6};
		int a = 2;
		try{
			int z = 5/a;
			for(int i=0;i<10;i++){
				System.out.println(arry[i]);
			}
		}catch(ArithmeticException e){
			System.out.println("0不能作为被除数。");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("arry[]长度是5，数组下标越界了");
		}
		System.out.println("没有异常运行成功了。");
	}

}
