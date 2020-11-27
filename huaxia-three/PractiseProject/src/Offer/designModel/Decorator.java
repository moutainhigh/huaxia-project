/**
 * Title: Decorator.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��29������3:52:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:Decorator2020��9��29��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��29������3:52:31
 */
public class Decorator implements Sourceable2 {
	private Sourceable2 source;
	/**
	 * Constructor
	 */
	public Decorator() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public Decorator(Sourceable2 source) {
		// TODO Auto-generated constructor stub
		this.source = source;
	}
	/* (non-Javadoc)
	 * @see Offer.designModel.Sourceable2#createComputer()
	 */
	@Override
	public void createComputer() {
		// TODO Auto-generated method stub
		source.createComputer();
		System.out.println("make system.");
	}
	/**
	 * @Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��29������3:56:02
	 */
	public static void main(String args[]){
		Sourceable2 source = new Source2();
		Sourceable2 obj = new Decorator(source);
		obj.createComputer();
	}
}
