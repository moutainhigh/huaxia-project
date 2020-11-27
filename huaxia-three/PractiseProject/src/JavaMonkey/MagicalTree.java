/**
 * Title: MagicalTree.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月14日上午10:56:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:MagicalTree2020年9月14日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月14日上午10:56:04
 */
public class MagicalTree {
	int total;//表示总的人参果数目
	int hiddenNumber = 0;//表示隐藏到泥土中的人参果数目
	/**
	 * Constructor
	 */
	public MagicalTree() {
		// TODO Auto-generated constructor stub
	}
	public MagicalTree(int total) {
		// TODO Auto-generated constructor stub
		this.total = total;
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月14日上午10:56:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MagicalTree tree= new MagicalTree(28);
		tree.beEaten(1,"清风");
		tree.beEaten(1,"明月");
		tree.hide(1,"悟空");
		tree.beEaten(1,"悟空");
		tree.beEaten(1,"八戒");
		tree.beEaten(1,"沙僧");
		tree.hide(tree.total,"悟空");
		tree.recover("观音菩萨");
		System.out.println("人参果树上有"+tree.total+"个人参果");
	} 
	/**
	 * @Title: beEaten
	 *@Description: TODO
	 *@param number
	 *@param who
	 *@author: LiuWei
	 *@Date: 2020年9月14日上午10:58:52
	 */
	public void beEaten(int number,String who){
		total = total - number;
		System.out.println(who+"吃掉"+number+"个人参果");
	}
	/**
	 * @Title: hide
	 *@Description: TODO
	 *@param number
	 *@param who
	 *@author: LiuWei
	 *@Date: 2020年9月14日上午11:00:49
	 */
	public void hide(int number,String who){
		total = total - number;
		hiddenNumber = hiddenNumber+ number;
		System.out.println(who+"使"+number+"个人参果隐藏到泥土里");
	}
	/***
	 * @Title: recover
	 *@Description: TODO
	 *@param who
	 *@author: LiuWei
	 *@Date: 2020年9月14日上午11:02:04
	 */
	public void recover(String who){
		total = total+hiddenNumber;
		hiddenNumber = 0;
		System.out.println(who+"医治果树");
	}
}
