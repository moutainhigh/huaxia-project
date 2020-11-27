/**
 * Title: ConcreteSubject.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������4:36:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:ConcreteSubject2020��10��12��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������4:36:03
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
