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
 * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 5
	at BaseMianShi341.Abnormal.main(Abnormal.java:38)
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午10:37:02
 */
public class Abnormal {

	/**
	 * Constructor
	 */
	public Abnormal() {
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
		for(int i=0;i<10;i++){
			System.out.println(arry[i]);
		}
	}

}
