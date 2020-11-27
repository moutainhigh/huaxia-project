/**
 * Title: Secretary.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��6������5:11:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacadePattern;

/**
 * @class_name:Secretary2020��1��6��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��6������5:11:19
 */
public class Secretary {
	private Chaufferu chauffeur = new Chaufferu();
	private Hotel hotel =new Hotel();
	private Restaurant restaurent = new Restaurant();
	private Airport airport  = new Airport();
	/**
	 * @Title: trip
	 *@Description: TODO���ų���
	 *@param to
	 *@param days
	 *@author: LiuWei
	 *@Date: 2020��1��6������5:13:45
	 */
	public void trip(String to,int days){
		airport.bookTicker("�ൺ", to);
		chauffeur.drive("����");
		hotel.reserve(10);
	}
	/**
	 * @Title: repast
	 *@Description: TODO���ŷ���
	 *@param num
	 *@author: LiuWei
	 *@Date: 2020��1��6������5:14:46
	 */
	public void repast(int num){
		restaurent.reserve(num);
		chauffeur.drive("�Ƶ�");
	}
	/**
	 * 
	 */
	public Secretary() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��1��6������5:11:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
