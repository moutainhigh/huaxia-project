/**
 * Title: FX.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��19������9:46:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Passage;

/**
 * @class_name:FX2020��8��19��
 * @comments:�����������֮��������ǧ������С��������λ���洢�ġ�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��19������9:46:01
 */
public class FX {
	/**
	 * @Title: fx
	 *@Description: TODO  ������ѧ����
	 *@param x
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��8��19������9:47:03
	 */
	public static double fx(double x){
		if( x< 0.5)
			return 2*x;
		else
			return 2*x -1;
	}
	/**
	 * Constructor
	 */
	public FX() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��19������9:46:01
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x = 0.1;
		for(int i=0;i<80;i++){
			System.out.println(x);
			x = fx(x);
		}
	}
}
