/**
 * Title: MarriageAgency.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日上午9:44:31
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.Mediator;

/**
 * @class_name:MarriageAgency2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日上午9:44:31
 */
public interface  MarriageAgency {
	/**
	 * @Title: pair
	 *@Description: TODO
	 *@param person
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午9:46:53
	 */
	public void pair(Person person);//为person配对
	/**
	 * @Title: register
	 *@Description: TODO
	 *@param person
	 *@author: LiuWei
	 *@Date: 2020年1月8日上午9:47:04
	 */
	public void register(Person person);//注册会员
}
