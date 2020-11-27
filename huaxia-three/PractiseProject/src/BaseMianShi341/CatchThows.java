/**
 * Title: CatchThows.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午11:18:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:CatchThows2020年9月23日
 * @comments:
 * @param:测试throw关键字
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午11:18:49
 */
public class CatchThows {
	static int x;
	/**
	 * Constructor
	 */
	public CatchThows() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 * @throws Exception 
	 *@Date: 2020年9月23日上午11:18:49
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		double a = Math.random()*10;
		if( x >0)
			System.out.println(a/x);
		else throw new Exception("Hello World!");
	}
	/**
	 * @Title: setX
	 *@Description: TODO
	 *@param x
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午11:20:31
	 */
	public void setX(int x){
		this.x = x;
	}
}
