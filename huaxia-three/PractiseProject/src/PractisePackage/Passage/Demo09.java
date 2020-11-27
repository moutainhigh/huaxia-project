/**
 * Title: Demo09.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月18日下午4:06:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:Demo092020年8月18日
 * @comments: 递推求年龄
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月18日下午4:06:39
 */
public class Demo09 {
	/**
	 * @Title: getAge
	 *@Description: TODO 递归求年龄
	 *@param n
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年8月18日下午4:07:38
	 */
	public static int getAge(int n){
		if(n == 1)
			return 10;
		return 2+ getAge(n-1);
	}
	/**
	 * Constructor
	 */
	public Demo09() {
		// TODO Auto-generated constructor stub
	}
	

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月18日下午4:06:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("第五个年龄为"+getAge(5));
	}
}
