/**
 * Title: SuperManFactory.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��1��8������4:37:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.SuperMan.abstractFactory;

/**
 * @class_name:SuperManFactory2020��1��8��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��1��8������4:37:42
 */
public interface HeroFactory {
	/**
	 * @Title: createSuperMan
	 *@Description: TODO
	 *@return
	 *��������
	 *@author: LiuWei
	 *@Date: 2020��1��8������4:38:03
	 */
	public ISuperMan createSuperMan();
	/**
	 * @Title: createSpiderMan
	 *@Description: TODO
	 *@return
	 *@author: LiuWei
	 *@Date: 2020��1��8������5:25:53
	 */
	public ISpiderMan createSpiderMan();
}
