/**
 * Title: AbstractTemplate.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月12日下午3:48:36
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer.designModel;

/**
 * @class_name:AbstractTemplate2020年10月12日
 * @comments:模板实现，定义模板，根据模板实现不同的方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月12日下午3:48:36
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
	 *@Date: 2020年10月12日下午3:51:28
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
	 *@Date: 2020年10月12日下午3:49:36
	 */
	public void checkNumber(){
		System.out.println("checkNumber.....");
	}
	/**
	 * @Title: queueUp
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午3:50:00
	 */
	public void queueUp(){
		System.out.println("queue up....");
	}
	public abstract void handleBusiness();
	/**
	 * @Title: serviceEvaluation
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年10月12日下午3:50:55
	 */
	public void serviceEvaluation(){
		System.out.println("business finished,service evaluation....");
	}
}
