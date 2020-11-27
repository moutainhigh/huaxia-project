/**
 * Title: MagicalTree.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��14������10:56:04
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package JavaMonkey;

/**
 * @class_name:MagicalTree2020��9��14��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��14������10:56:04
 */
public class MagicalTree {
	int total;//��ʾ�ܵ��˲ι���Ŀ
	int hiddenNumber = 0;//��ʾ���ص������е��˲ι���Ŀ
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
	 *@Date: 2020��9��14������10:56:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MagicalTree tree= new MagicalTree(28);
		tree.beEaten(1,"���");
		tree.beEaten(1,"����");
		tree.hide(1,"���");
		tree.beEaten(1,"���");
		tree.beEaten(1,"�˽�");
		tree.beEaten(1,"ɳɮ");
		tree.hide(tree.total,"���");
		tree.recover("��������");
		System.out.println("�˲ι�������"+tree.total+"���˲ι�");
	} 
	/**
	 * @Title: beEaten
	 *@Description: TODO
	 *@param number
	 *@param who
	 *@author: LiuWei
	 *@Date: 2020��9��14������10:58:52
	 */
	public void beEaten(int number,String who){
		total = total - number;
		System.out.println(who+"�Ե�"+number+"���˲ι�");
	}
	/**
	 * @Title: hide
	 *@Description: TODO
	 *@param number
	 *@param who
	 *@author: LiuWei
	 *@Date: 2020��9��14������11:00:49
	 */
	public void hide(int number,String who){
		total = total - number;
		hiddenNumber = hiddenNumber+ number;
		System.out.println(who+"ʹ"+number+"���˲ι����ص�������");
	}
	/***
	 * @Title: recover
	 *@Description: TODO
	 *@param who
	 *@author: LiuWei
	 *@Date: 2020��9��14������11:02:04
	 */
	public void recover(String who){
		total = total+hiddenNumber;
		hiddenNumber = 0;
		System.out.println(who+"ҽ�ι���");
	}
}
