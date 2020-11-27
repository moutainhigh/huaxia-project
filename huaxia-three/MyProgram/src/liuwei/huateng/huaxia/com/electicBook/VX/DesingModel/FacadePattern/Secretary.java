/**
 * Title: Secretary.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日下午5:11:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.FacadePattern;

/**
 * @class_name:Secretary2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日下午5:11:19
 */
public class Secretary {
	private Chaufferu chauffeur = new Chaufferu();
	private Hotel hotel =new Hotel();
	private Restaurant restaurent = new Restaurant();
	private Airport airport  = new Airport();
	/**
	 * @Title: trip
	 *@Description: TODO安排出差
	 *@param to
	 *@param days
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午5:13:45
	 */
	public void trip(String to,int days){
		airport.bookTicker("青岛", to);
		chauffeur.drive("机场");
		hotel.reserve(10);
	}
	/**
	 * @Title: repast
	 *@Description: TODO安排饭局
	 *@param num
	 *@author: LiuWei
	 *@Date: 2020年1月6日下午5:14:46
	 */
	public void repast(int num){
		restaurent.reserve(num);
		chauffeur.drive("酒店");
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
	 *@Date: 2020年1月6日下午5:11:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
