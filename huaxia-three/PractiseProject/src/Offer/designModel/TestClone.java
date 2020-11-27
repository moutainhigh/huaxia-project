/**
 * Title: TestClone.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月29日下午2:17:50
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:TestClone2020年9月29日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月29日下午2:17:50
 */
public class TestClone {

	/**
	 * Constructor
	 */
	public TestClone() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月29日下午2:17:50
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Computer3 computer = new Computer3("8Core","16G","1TB");
		System.out.println("before simple clone"+computer.toString());
		System.out.println("before simple clone"+computer.hashCode());

		try {
			Computer3 computerClone = (Computer3) computer.clone();
			System.out.println("after simple clone"+computerClone.toString());
			System.out.println("after simple clone"+computerClone.hashCode());
			Disk disk = new Disk("208G","2TB");
			ComputerDetail computerDetail = new ComputerDetail("12core","64G",disk);
			System.out.println("before deep clone"+computerDetail.toString());
			System.out.println("before deep clone"+computerDetail.hashCode());
			ComputerDetail computerDeatailClone = (ComputerDetail)computerDetail.clone();
			System.out.println("after deep clone"+computerDeatailClone.toString());
			System.out.println("after deep clone"+computerDeatailClone.hashCode());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
