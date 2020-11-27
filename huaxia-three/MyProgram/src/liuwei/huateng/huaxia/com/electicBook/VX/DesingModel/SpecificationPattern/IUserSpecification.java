/**
 * Title: IUserSpecification.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��9������9:37:30
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SpecificationPattern;

/**
 * @class_name:IUserSpecification2020��1��9��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��9������9:37:30
 */
public interface IUserSpecification {
	/**
	 * @Title: isSatisfiedBy
	 *@Description: TODO��ѡ���Ƿ�����Ҫ��
	 *@param user
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��9������9:39:13
	 */
	public boolean isSatisfiedBy(User user);
	/**
	 * @Title: and
	 *@Description: TODOand����
	 *@param spec
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��9������9:39:26
	 */
	public IUserSpecification and(IUserSpecification spec);
	/**
	 * @Title: or
	 *@Description: TODOor����
	 *@param spec
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��9������9:39:36
	 */
	public IUserSpecification or(IUserSpecification spec);
	/**
	 * @Title: not
	 *@Description: TODO
	 *not����
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��9������9:39:50
	 */
	public IUserSpecification not();
}
