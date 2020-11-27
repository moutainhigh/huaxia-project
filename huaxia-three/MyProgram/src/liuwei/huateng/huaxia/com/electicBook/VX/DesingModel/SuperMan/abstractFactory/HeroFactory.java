/**
 * Title: SuperManFactory.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月8日下午4:37:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.abstractFactory;

/**
 * @class_name:SuperManFactory2020年1月8日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月8日下午4:37:42
 */
public interface HeroFactory {
	/**
	 * @Title: createSuperMan
	 *@Description: TODO
	 *@return
	 *生产超人
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午4:38:03
	 */
	public ISuperMan createSuperMan();
	/**
	 * @Title: createSpiderMan
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020年1月8日下午5:25:53
	 */
	public ISpiderMan createSpiderMan();
}
