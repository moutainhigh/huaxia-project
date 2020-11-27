/**
 * Title: SubDog1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日上午10:11:27
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:SubDog12020年9月23日
 * @comments:
 * @param:子类覆盖重写父类的方法
 * @return:
 * runResult：我的毛是黑色的
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日上午10:11:27
 */
public class SubDog1 extends Dog1 {

	/**
	 * Constructor
	 */
	public SubDog1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月23日上午10:11:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str=new SubDog1().color();
		System.out.println(str);
	}
	/**
	 * 重写覆盖父类的方法
	 */
	public String color(){
		return "我的毛是黑色的";
	}
}
