/**
 * Title: Boss.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午5:15:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacadePattern;

/**
 * @class_name:Boss2020年1月6日
 * @comments：外观模式，32种设计模式之一
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午5:15:32
 */
public class Boss {

	/**
	 * 
	 */
	public Boss() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午5:15:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Secretary s = new Secretary();
		System.out.println("老板告诉秘书要到上海去出差");
		s.trip("上海 ", 10);
		System.out.println("---------------------------------")	;
		System.out.println("老板告诉秘书要请八个人去吃饭");
		s.repast(8);
	}

}
