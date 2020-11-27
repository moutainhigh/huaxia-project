/**
 * Title: DrumBeater.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月7日上午10:33:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:DrumBeater2020年1月7日
 * @comments: 责任链模式
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月7日上午10:33:02
 */
public class DrumBeater {

	/**
	 * 
	 */
	public DrumBeater() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月7日上午10:33:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个链
		Player player = new PlayerA(new PlayerB(new PlayerC(new PlayerD(null))));
		//击鼓三下停止
		player.handle(3);
	}

}
