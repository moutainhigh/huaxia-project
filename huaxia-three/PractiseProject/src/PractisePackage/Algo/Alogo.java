/**
 * Title: Alogo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��7������10:49:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.Algo;

/**
 * @class_name:Alogo2020��8��7��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��7������10:49:38
 */
public class Alogo {
	private static int FETCH = 1000;
	private static double RATE = 0.0171;
	/**
	 * Constructor
	 */
	public Alogo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��7������10:49:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double corpus[] = new double[49];
		int i;
		corpus[48] = (double) FETCH;
		for(i = 47;i>0;i--){
			corpus[i] = (corpus[i+1]+FETCH)/(1+RATE/12);
		}
		for(i =48;i>0;i--){
			System.out.println("��"+i+"����ĩ�����ϼ�"+corpus[i]);
		}
	}

}
