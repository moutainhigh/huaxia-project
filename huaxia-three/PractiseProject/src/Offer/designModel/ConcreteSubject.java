/**
 * Title: ConcreteSubject.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午4:36:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ConcreteSubject2020年10月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午4:36:03
 */
public class ConcreteSubject extends Subject {

	/**
	 * Constructor
	 */
	public ConcreteSubject() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Offer.designModel.Subject#notifyObserver(java.lang.String)
	 */
	@Override
	public void notifyObserver(String message) {
		// TODO Auto-generated method stub
		for(Object obs:obervers){
			System.out.println("notify observer "+message+"change..");	
			((Observer)obs).dataChange(message);
		}
	}

}
