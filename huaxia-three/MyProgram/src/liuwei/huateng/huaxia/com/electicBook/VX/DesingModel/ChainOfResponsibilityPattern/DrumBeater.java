/**
 * Title: DrumBeater.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��7������10:33:02
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.ChainOfResponsibilityPattern;

/**
 * @class_name:DrumBeater2020��1��7��
 * @comments: ������ģʽ
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��7������10:33:02
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
	 *@Date: 2020��1��7������10:33:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ����
		Player player = new PlayerA(new PlayerB(new PlayerC(new PlayerD(null))));
		//��������ֹͣ
		player.handle(3);
	}

}
