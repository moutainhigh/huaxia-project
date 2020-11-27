/**
 * Title: AbstractTemplate.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��12������3:48:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:AbstractTemplate2020��10��12��
 * @comments:ģ��ʵ�֣�����ģ�壬����ģ��ʵ�ֲ�ͬ�ķ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��12������3:48:36
 */
public abstract class AbstractTemplate {

	/**
	 * Constructor
	 */
	public AbstractTemplate() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @Title: templateMethod
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��10��12������3:51:28
	 */
	public void templateMethod(){
		checkNumber();
		queueUp();
		handleBusiness();
		serviceEvaluation();
	}
	/**
	 * @Title: checkNumber
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��10��12������3:49:36
	 */
	public void checkNumber(){
		System.out.println("checkNumber.....");
	}
	/**
	 * @Title: queueUp
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��10��12������3:50:00
	 */
	public void queueUp(){
		System.out.println("queue up....");
	}
	public abstract void handleBusiness();
	/**
	 * @Title: serviceEvaluation
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020��10��12������3:50:55
	 */
	public void serviceEvaluation(){
		System.out.println("business finished,service evaluation....");
	}
}
