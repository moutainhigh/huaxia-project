/**
 * Title: IUserSpecification.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月9日上午9:37:30
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern;

/**
 * @class_name:IUserSpecification2020年1月9日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月9日上午9:37:30
 */
public interface IUserSpecification {
	/**
	 * @Title: isSatisfiedBy
	 *@Description: TODO候选者是否满足要求
	 *@param user
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月9日上午9:39:13
	 */
	public boolean isSatisfiedBy(User user);
	/**
	 * @Title: and
	 *@Description: TODOand操作
	 *@param spec
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月9日上午9:39:26
	 */
	public IUserSpecification and(IUserSpecification spec);
	/**
	 * @Title: or
	 *@Description: TODOor操作
	 *@param spec
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月9日上午9:39:36
	 */
	public IUserSpecification or(IUserSpecification spec);
	/**
	 * @Title: not
	 *@Description: TODO
	 *not操作
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月9日上午9:39:50
	 */
	public IUserSpecification not();
}
