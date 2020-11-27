/**
 * Title: Test16.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日下午3:34:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Test162020年8月18日
 * @comments:  一个数，加上100是一个完全平方数，再加上168又是一个完全平方数
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日下午3:34:02
 */
public class Test16 {

	/**
	 * Constructor
	 */
	public Test16() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日下午3:34:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = -100;i<10000;i++){
			if(Math.sqrt(i+100)%1 ==0 && Math.sqrt(i+268)%1 == 0){
				System.out.println(i+" "+(i+100)+" "+Math.sqrt(i+100)+" "+(i+268)+" "+Math.sqrt(i+268));
			}
		}
	}
}
