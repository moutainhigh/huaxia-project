/**
 * Title: AbstractFactory.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月6日上午9:46:08
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel;

/**
 * @class_name:AbstractFactory2020年1月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月6日上午9:46:08
 */
public interface AbstractFactory {
	//创建产品A
	public ProductA factoryA();
	public ProductB factoryB();
}
